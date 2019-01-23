#!/bin/bash

declare -A groups
function addGroup() {
    local key=${1}
    local value=${2}

    groups["${key}"]="${value}"
}

function getGroup() {
    local key=${1}
    echo "${groups[${key}]}"
}

addGroup "001" "001-hello-world"
addGroup "002" "002-controllers"
addGroup "003" "003-module-basics"
addGroup "004" "004-module-communication"
addGroup "005" "005-shared-preferences"
addGroup "006" "006-notifications-api"
addGroup "007" "007-adapter-based-views"
addGroup "008" "008-custom-attributes"
addGroup "009" "009-common-use-cases"
addGroup "050" "050-google-maps"
addGroup "999" "999-advance-stuff"