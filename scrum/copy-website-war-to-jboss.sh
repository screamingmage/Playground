#!/bin/bash

#This script copies the Website war into the jboss deployments folder

gradle clean ec build

echo 'Copying website war to jboss deployments folder'
cp /home/Sulaiman/scrum/website/build/libs/website-1.0.war /cygdrive/c/opt/jboss-as/standalone/deployments/
echo 'Done'
