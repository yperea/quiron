#!/bin/bash

set -e

mkdir -p /var/codedeploy/quiron

cat <<EOF >/var/codedeploy/quiron/env.properties
APPLICATION_NAME=$APPLICATION_NAME
DEPLOYMENT_GROUP_NAME=$DEPLOYMENT_GROUP_NAME
DEPLOYMENT_ID=$DEPLOYMENT_ID
EOF
