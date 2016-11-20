#!/bin/bash

mkdir /opt/docker
mkdir /opt/docker/unity
mkdir /opt/docker/unity/neo4j
mkdir /opt/docker/unity/neo4j-test

chown -R ${USER}:users /opt/docker/unity
chmod -R 777 /opt/docker/unity