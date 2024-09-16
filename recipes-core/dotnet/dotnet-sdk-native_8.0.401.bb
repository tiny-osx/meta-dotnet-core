DESCRIPTION = "Microsoft .NET Core 8.0 SDK including .NET Runtime"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require dotnet.inc

# https://download.visualstudio.microsoft.com/download/pr/db901b0a-3144-4d07-b8ab-6e7a43e7a791/4d9d1b39b879ad969c6c0ceb6d052381/dotnet-sdk-8.0.401-linux-x64.tar.gz
SRC_FILE:x86-64 ?= "dotnet-sdk-${PV}-linux-x64.tar.gz"
SRC_FETCH_ID:x86-64 = "db901b0a-3144-4d07-b8ab-6e7a43e7a791/4d9d1b39b879ad969c6c0ceb6d052381"
SRC_SHA512SUM:x86-64 = "4d2180e82c963318863476cf61c035bd3d82165e7b70751ba231225b5575df24d30c0789d5748c3a379e1e6896b57e59286218cacd440ffb0075c9355094fd8c"

inherit native

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${bindir}
    tar --no-same-owner -xvzf ${WORKDIR}/${SRC_FILE} -C ${D}${bindir}
    chmod +x ${D}${bindir}/dotnet
}
