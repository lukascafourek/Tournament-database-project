import random
import psycopg2

def generate_player_data(data, trener):
    return {
        "cislo_smlouvy": data[0],
        "hodnoceni": random.uniform(4.0, 5.0),
        "trener": trener[0]
    }

conn = psycopg2.connect(
    database="cafoulu1",
    user="cafoulu1",
    password="<PASSWORD>",
    host="slon.felk.cvut.cz",
    port="5432"
)
cursor = conn.cursor()
cursor.execute("SELECT cislo_smlouvy FROM profesional OFFSET 12")
results = cursor.fetchall()
data = []
for row in results:
    data.append(row)
for i in range(18):
    if i < 5: player_data = generate_player_data(data[i], data[5])
    elif 5 < i < 11: player_data = generate_player_data(data[i], data[11])
    elif 11 < i < 17: player_data = generate_player_data(data[i], data[17])
    else: continue
    cursor.execute("""
        INSERT INTO hrac (cislo_smlouvy, hodnoceni, trener)
        VALUES (%s, %s, %s)""", tuple(player_data.values()))
conn.commit()
conn.close()
