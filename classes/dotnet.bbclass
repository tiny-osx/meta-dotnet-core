DESCRIPTION = "Microsoft .NET Core 8.0 SDK Cross Compiler"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "dotnet-sdk-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_PACKAGE_STRIP = "1"

RDEPENDS:${PN} = "\
    glibc \
    icu \
    krb5 \
    libgcc \
    libstdc++ \
    ca-certificates \
    openssl \
"
INSANE_SKIP:${PN} += " \
    file-rdeps \
    already-stripped \
"

do_compile[network] = "1" 

python () {
    target_arch = d.getVar("TARGET_ARCH")
    if "x86_64" in target_arch:
        d.appendVar('BUILD_TARGET', "linux-x64")
        return
    if "aarch64" in target_arch:
        d.appendVar('BUILD_TARGET', "linux-arm64")
        return
    if "arm" in target_arch:
        d.appendVar('BUILD_TARGET', "linux-arm")
        return

    bb.fatal("Architecture not supported: " + target_arch)
}