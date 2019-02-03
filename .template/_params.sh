#!/bin/bash

debug=true
group=
noModule=
packageName=
appClassName=
tutorialName=
params=(group packageName appClassName tutorialName noModule)

function extractParams() {
    for paramValue in "${@}"; do
        case "${paramValue}" in
            "--group="*)
                group=`echo "${paramValue}" | sed -E "s/--group=(.*)/\1/"`
            ;;

            "--name="*)
                tutorialName=`echo "${paramValue}" | sed -E "s/--name=(.*)/\1/"`
            ;;

            "--no-module")
                noModule=true
            ;;

            "--debug")
                debug="true"
            ;;
        esac
    done
}