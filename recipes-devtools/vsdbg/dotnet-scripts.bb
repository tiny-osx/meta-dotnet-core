DESCRIPTION = "Microsoft .NET Core Install Scripts"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

BB_STRICT_CHECKSUM = "0"

SRC_URI = "\
    https://dot.net/v1/dotnet-install.sh \
    https://aka.ms/getvsdbgsh \
"

RDEPENDS:${PN} = "\
    curl \
    icu \
    libgssapi-krb5 \
    zlib \
"

FILES:${PN} += "\
    ${datadir}/ \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {    
    install -d ${D}${datadir}
    install -m 0770 ${WORKDIR}/dotnet-install.sh ${D}/${datadir}/dotnet-install.sh
    install -m 0770 ${WORKDIR}/getvsdbgsh ${D}/${datadir}/getvsdbg.sh
}