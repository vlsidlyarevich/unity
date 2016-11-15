FROM debian:jessie
MAINTAINER Vladislav Sidlyarevich vlsidlyarevich@gmail.com

RUN apt-get update && apt-get install -y wget
RUN wget -O - https://debian.neo4j.org/neotechnology.gpg.key | apt-key add -
RUN echo 'deb http://debian.neo4j.org/repo stable/' | tee /etc/apt/sources.list.d/neo4j.list
RUN apt-get update
RUN apt-get install -y neo4j
EXPOSE 7474 7473 7687

CMD ["neo4j"]