import psycopg2

conn = psycopg2.connect(
    database="cafoulu1",
    user="cafoulu1",
    password="<PASSWORD>",
    host="slon.felk.cvut.cz",
    port="5432"
)
cursor = conn.cursor()
cursor.execute("SELECT cislo_smlouvy FROM hrac OFFSET 10")
results = cursor.fetchall()
players = [row[0] for row in results]
teams = [players[i:i+5] for i in range(0, len(players), 5)]
for team in teams:
    for player1 in team:
        for player2 in team:
            if player1 != player2:
                cursor.execute("""
                    INSERT INTO je_spoluhracem (hrac, spoluhrac)
                    VALUES (%s, %s)""", (player1, player2))
conn.commit()
conn.close()
