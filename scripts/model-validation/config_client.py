import sys

import pymysql
import yaml


def read_config():
    with open('config.yaml') as conf_file:
        db_configs = yaml.safe_load(conf_file)
        return db_configs


def find_temporal_database_config(database_name):
    configs = read_config()['conf']['databases']['temporal']
    db_config = filter(lambda conf: conf['db_name'] == database_name, configs)
    return list(db_config)[0]


def get_data_directory():
    return read_config()['conf']['data_directory']


def get_temporal_database_names():
    configs = read_config()['conf']['databases']['temporal']
    db_names = map(lambda conf: conf['db_name'], configs)
    return list(db_names)


def get_oltp_database_name():
    config = read_config()['conf']['databases']['oltp']
    return config['db_name']


def create_database_connection(database_name, db_type):
    """Create new DB connection"""
    if db_type == 'OLTP':
        config = read_config()['conf']['databases']['oltp']
    elif db_type == 'TEMPORAL':
        config = find_temporal_database_config(database_name)

    try:

        print(f"Database Config Loaded For :{database_name}")
        conn = pymysql.connect(host=config['db_host'], port=config['db_port'], database=config['db_name'],
                               user=config['db_user'],
                               password=config['db_password'])
        return conn
    except pymysql.DatabaseError as e:
        print(f'Error {e}')
        sys.exit(1)

# if __name__ == '__main__':
# print(read_config())
# print(get_data_directory())
# print(find_temporal_database_config('dd')['db_name'])
# print(get_temporal_database_names())
