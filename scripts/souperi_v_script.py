import psycopg2

conn = psycopg2.connect(
    database="cafoulu1",
    user="cafoulu1",
    password="<PASSWORD>",
    host="slon.felk.cvut.cz",
    port="5432"
)
cursor = conn.cursor()
cursor.execute("SELECT id, tym_A, tym_b FROM zapas ORDER BY id ASC")
results = cursor.fetchall()
for result in results:
    cursor.execute("""INSERT INTO souperi_v VALUES (%s, %s)""", (result[0], result[1]))
    cursor.execute("""INSERT INTO souperi_v VALUES (%s, %s)""", (result[0], result[2]))
    conn.commit()
conn.close()
