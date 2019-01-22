#!/bin/bash

debug=true
group=
packageName=
appClassName=
tutorialName=
params=(group packageName appClassName tutorialName)

function extractParams() {
    for paramValue in "${@}"; do
        case "${paramValue}" in
            "--group="*)
                group=`echo "${paramValue}" | sed -E "s/--group=(.*)/\1/"`
            ;;

            "--name="*)
                tutorialName=`echo "${paramValue}" | sed -E "s/--name=(.*)/\1/"`
            ;;

            "--debug")
                debug="true"
            ;;
        esac
    done
}