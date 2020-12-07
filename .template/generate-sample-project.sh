#!/bin/bash

source ${BASH_SOURCE%/*}/../dev-tools/scripts/_core-tools/_source.sh
source ${BASH_SOURCE%/*}/_groups.sh
source ${BASH_SOURCE%/*}/_params.sh

function printUsage() {
    logVerbose
    logVerbose "   USAGE:"
    logVerbose "     ${BBlack}bash${NoColor} ${BCyan}${0}${NoColor} ${groupParam} ${tutorialNameParam}"
    logVerbose
    logVerbose
    exit 0
}

function verifyRequirement() {
    local missingParamColor=${BRed}
    local existingParamColor=${BBlue}

    missingData=
    if [[ ! "${group}" ]]; then
        group="${missingParamColor}tutorial-group"
        missingData=true
    fi

    if [[ ! "${tutorialName}" ]]; then
        tutorialName="${missingParamColor}tutorial-name"
        missingData=true
    fi

    if [[ "${missingData}" ]]; then
        groupParam="--group=${existingParamColor}${group}${NoColor}"
        tutorialNameParam="--name=${existingParamColor}${tutorialName}${NoColor}"
        printUsage
    fi
}

data=(group groupFolder tutorialName appName appClassName resourceName packageName packageNameAsPath)
function processParams() {
    groupFolder=`getGroup "${group}"`
    if [[ ! "${groupFolder}" ]]; then logError "No such group ${group}" && exit 2; fi

    _tutorialName=(${tutorialName//-/ })
    className=
    resourceName=
    appName=
    packageName="com.nu.art.cyborg.tutorial."
    for (( arg=0; arg<${#_tutorialName[@]}; arg+=1 )); do
        local part="${_tutorialName[${arg}]}"
        className=${className}${part^}
        appName="${appName} ${part^}"
        resourceName=${resourceName}_${part}
        if [[ ${arg} == 0 ]]; then
            packageName="${packageName}${part}"
        else
            packageName="${packageName}${part^}"
        fi
    done

    appName=${appName:1}
    packageNameAsPath=`echo ${packageName} | sed -E "s/\./\//g"`

}



extractParams "$@"
verifyRequirement

processParams
printDebugParams ${debug} "${data[@]}"

signature

function deleteFolderStructure() {
    local outputFolder=${1}
    rm -rf ${outputFolder}
}

function createFolderStructure() {
    # File name as well a text in files
    local placeholder_ClassName="PLACEHOLDER_CLASS_NAME"
    local placeholder_Resource="PLACEHOLDER_RESOURCE_NAME"

    # Text in files
    local placeholder_TutorialName="PLACEHOLDER_TUTORIAL_NAME"
    local placeholder_PackageName="PLACEHOLDER_PACKAGE_NAME"
    local placeholder_AppName="PLACEHOLDER_APP_NAME"
    local placeholder_Group="PLACEHOLDER_GROUP"

    local outputFolder=${1}
    local rootFolder="${outputFolder}/${groupFolder}/${tutorialName}"
    local mainFolder="${rootFolder}/src/main"

    local javaFolder="${mainFolder}/java"
    local sourcesDir=${javaFolder}/${packageNameAsPath}

    local resFolder="${mainFolder}/res"

    function prepare() {
        deleteFolderStructure "${rootFolder}"

        createDir ${sourcesDir}
        createDir ${resFolder}
    }

    function copyFiles() {
        local template=template-project
        if [[ "${noModule}" ]]; then
            template=template-project-no-module
        fi
        cp ${BASH_SOURCE%/*}/${template}/src/main/java/* "${sourcesDir}"
        cp -r ${BASH_SOURCE%/*}/${template}/src/main/res/* ${resFolder}
        cp ${BASH_SOURCE%/*}/${template}/src/main/AndroidManifest.xml ${mainFolder}
        cp ${BASH_SOURCE%/*}/${template}/.gitignore ${rootFolder}
        cp ${BASH_SOURCE%/*}/${template}/*.* ${rootFolder}
    }

    function renamePlaceholders() {
        renameFiles ${rootFolder} ${placeholder_ClassName} ${className}
        renameFiles ${rootFolder} ${placeholder_Resource} ${resourceName}

        string_replaceAll ${rootFolder} ${placeholder_TutorialName} "${tutorialName}" "%"
        string_replaceAll ${rootFolder} ${placeholder_ClassName} "${className}" "%"
        string_replaceAll ${rootFolder} ${placeholder_AppName} "${appName}" "%"
        string_replaceAll ${rootFolder} ${placeholder_PackageName} "${packageName}" "%"
        string_replaceAll ${rootFolder} ${placeholder_Group} "${group}" "%"
        string_replaceAll ${rootFolder} ${placeholder_Resource} "${resourceName}" "%"
    }

    function applyToSettings() {
        local gradleRootSettingsFile="${BASH_SOURCE%/*}/../settings.gradle"
        local gradleSettingsFile="${rootFolder}/settings.gradle"
        echo "include ':${tutorialName}'" >> ${gradleSettingsFile}
        echo "project(':${tutorialName}').projectDir = ext.settingsDir" >> ${gradleSettingsFile}

        if [[ ! `cat ${gradleRootSettingsFile} | grep "${gradleSettingsFile}"` ]]; then
            echo "applyNestedSettingsIfExists(settingsDir.getAbsolutePath() + '/${gradleSettingsFile}')" >> ${gradleRootSettingsFile}
        fi
    }

    prepare
    copyFiles
    renamePlaceholders
    applyToSettings
}

createFolderStructure "tutorials"
