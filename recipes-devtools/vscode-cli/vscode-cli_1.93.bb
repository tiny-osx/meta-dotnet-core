DESCRIPTION = "Visual Studio Code CLI on Linux"
HOMEPAGE = "https://code.visualstudio.com/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI[sha256sum] = "18eaba04f2240bf7cb0e5cd7fad5720027fc1d5e89401d232c4fe31dcb6d4317"
SRC_URI += " \
    https://update.code.visualstudio.com/${PV}/cli-linux-arm64/stable;downloadfilename=vscode_cli_linux_arm64_cli.tar.gz \
    file://code-server.init \
    file://code-tunnel.init \
"
COMPATIBLE_HOST ?= "(aarch64).*-linux"

FILES:${PN} += "\
    ${bindir} \
    ${datadir}/vscode \
"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
    
    install -d ${D}${sysconfdir}/init.d/
    install -c -m 0755 ${WORKDIR}/code-server.init ${D}${sysconfdir}/init.d/code-server
    install -c -m 0755 ${WORKDIR}/code-tunnel.init ${D}${sysconfdir}/init.d/code-tunnel
    
    install -d ${D}${datadir}/vscode
    install -m 0770 ${WORKDIR}/code ${D}${datadir}/vscode 

    install -d ${D}${bindir}
    ln -rs ${D}${datadir}/vscode/code ${D}${bindir}/code
}

inherit update-rc.d

INITSCRIPT_NAME = "code-server"
INITSCRIPT_PARAMS = "start 99 5 2 . stop 20 0 1 6 ."

INSANE_SKIP:${PN} += "already-stripped ldflags"