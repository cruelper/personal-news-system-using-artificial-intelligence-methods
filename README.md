sudo systemctl start docker
sudo docker-compose up
sudo docker build -t mypostgres
sudo docker run -p 5432:5432 postgres
