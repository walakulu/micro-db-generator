import config_client



def execute_sql(file_name,db_con):
    print(f"Query Execution Started...:{file_name} ")
    with open(file_name) as infile:
        for sql in infile:
            cursor = db_con.cursor()
            cursor.execute(sql)
            db_con.commit()
    print(f"Query Execution Ended...:{file_name}")

def load_oltp_data():
    db_name=config_client.get_oltp_database_name()
    db_con = config_client.create_database_connection(db_name,'OLTP')
    #need to proceed through order to avoid key violation exceptions
    sql_file_name=f"sql/OLTP/transaction_type.sql"
    execute_sql(sql_file_name,db_con)
    sql_file_name=f"sql/OLTP/customer_profile.sql"
    execute_sql(sql_file_name,db_con)
    sql_file_name=f"sql/OLTP/wallet.sql"
    execute_sql(sql_file_name,db_con)
    sql_file_name=f"sql/OLTP/transaction.sql"
    execute_sql(sql_file_name,db_con)




def load_temporal_data():
    db_names = config_client.get_temporal_database_names()

    for db_name in db_names:
        db_con = config_client.create_database_connection(db_name,'TEMPORAL')
        temporal_period=db_name.split('_')[2]
        sql_file_name=f"sql/TEMPORAL/transaction_temporal_{temporal_period}_day.sql"
        execute_sql(sql_file_name,db_con)
        sql_file_name=f"sql/TEMPORAL/transaction_type_temporal_{temporal_period}_day.sql"
        execute_sql(sql_file_name,db_con)
        sql_file_name=f"sql/TEMPORAL/wallet_temporal_{temporal_period}_day.sql"
        execute_sql(sql_file_name,db_con)
        sql_file_name=f"sql/TEMPORAL/customer_profile_temporal_{temporal_period}_day.sql"
        execute_sql(sql_file_name,db_con)




if __name__ == '__main__':
    # load_oltp_data()
    load_temporal_data()
