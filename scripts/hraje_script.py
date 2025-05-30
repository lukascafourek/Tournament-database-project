import psycopg2

conn = psycopg2.connect(
    database="cafoulu1",
    user="cafoulu1",
    password="<PASSWORD>",
    host="slon.felk.cvut.cz",
    port="5432"
)
cursor = conn.cursor()
cursor.execute("SELECT id, id_zapasu FROM kolo ORDER BY id ASC OFFSET 48")
result1 = cursor.fetchall()
cursor.execute("SELECT id_zapasu, nazev_tymu FROM souperi_v ORDER BY id_zapasu ASC OFFSET 6")
result2 = cursor.fetchall()
cursor.execute("SELECT cislo_smlouvy FROM hrac")
result3 = cursor.fetchall()
kola = [(row[0], row[1]) for row in result1]
zapasy = [(row[0], row[1]) for row in result2]
hraci = [row[0] for row in result3]
query = "INSERT INTO hraje (hrac, id_kola) VALUES (%s, %s)"
data = []
for zapas in zapasy:
    for kolo in kola:
        if kolo[1] == zapas[0]:
            if zapas[1] == "Virtus Pro":
                for i in range(5):
                    data.append((hraci[i], kolo[0]))
            elif zapas[1] == "Natus Vincere":
                for i in range(5, 10):
                    data.append((hraci[i], kolo[0]))
            elif zapas[1] == "Astralis":
                for i in range(10, 15):
                    data.append((hraci[i], kolo[0]))
            elif zapas[1] == "FaZe Clan":
                for i in range(15, 20):
                    data.append((hraci[i], kolo[0]))
            elif zapas[1] == "Fnatic":
                for i in range(20, 25):
                    data.append((hraci[i], kolo[0]))
cursor.executemany(query, data)
conn.commit()
conn.close()
