SUMMARY = "Variscite MII tool"
DESCRIPTION = "A tool to read, write, and test MII interfaces on Variscite SOMs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1232ac6a89eecd5b83db0b7f84f3c36b"

SRC_URI = "git://github.com/varigit/var-mii;protocol=https;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "e62b6cfdef3d88b3e675c8fb5c75412d0d77984d"

S = "${WORKDIR}/git"

TARGET_CC_ARCH += "${LDFLAGS}"
