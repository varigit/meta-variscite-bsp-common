FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# Apply patches from Linux_SD-UART-BT-IW612-18.99.2.p19.10-18.99.2.p19.10-MXM5X18398.p7-GPL/OT-Tools_LNX_6_1_1-IMX8
SRC_URI = "\
	git://github.com/openthread/openthread.git;protocol=https;branch=main \
    file://0001-coex-buff-issue-fix.patch \
    file://0002-csl-debug_prints.patch \
    file://0003-set-vsc-ir-eui64.patch \
    file://0004-set-vsc-txpwr-limit.patch \
    file://0005-host-spi-clear-tx-buffer.patch \
    file://0006-ot-daemon-release-resources.patch \
    file://0008-add-mfg-cmd-for-pfw.patch \
    file://0010-tcp-connection-fix.patch \
    file://0011-get-vsc-txpwr-limit.patch \
    file://0012-vsc-for-otbr.patch \
    file://0013-cca-configuration.patch \
    file://0015-get-vsc-fwversion.patch \
    file://0016-lnt-fix-read-settings.patch \
    file://0017-lnt-buffer-handle-when-recover.patch \
    file://0019-remove-toggle-reset-pin.patch \
    file://0020-lnt-fix-spi-latency.patch \
    file://0021-add-OT_TCP-option-parsing.patch \
    file://0023-csl-ahead-time.patch \
    file://0024-spi-new-design.patch \
    file://0025-spi-default-packet-size.patch \
    file://0026-128-sed.patch \
    file://0027-swicth-to-PAN-ch-after-CSL-tx.patch \
    file://0028-fix-98e644d538-host-compilation-issue.patch \
    file://0029-fix-get-txpwrlimit-ret.patch \
    file://0030-range-check-for-rf-test-mode.patch \
    file://0031-Fix-OT-Key-ID-Mode-2-Security-Vulnerability.patch \
    file://0032-cca3-configuration.patch \
"

# Variscite patch to make reset gpio optional
SRC_URI += "\
    file://0001-ot-daemon-spi_interface-make-reset-device-and-line-o.patch \
"

# Align with Linux_SD-UART-BT-IW612-18.99.2.p19.10-18.99.2.p19.10-MXM5X18398.p7-GPL-Releasenotes_1.1.pdf
SRCREV = "98e644d538ff2c57e96c54bdd6e13df31c7d19ba"

# ot-daemon_%.bbappend

# Align with Software User Manual for IW612 802.15.4 Radio (UM11844)
EXTRA_OECMAKE:append = " \
    -DOT_DAEMON=ON \
    -DOT_POSIX_CONFIG_RCP_BUS=SPI \
    -DOT_COMPILE_WARNING_AS_ERROR=OFF \
    -DMBEDTLS_FATAL_WARNINGS=OFF \
    -DOT_THREAD_VERSION=1.2 \
    LINK_METRICS_INITIATOR=1 \
    LINK_METRICS_SUBJECT=1 \
    MLR=1 \
    DUA=1 \
    BACKBONE_ROUTER=1 \
    RCP_RESTORATION_MAX_COUNT=5 \
    OT_TCP=0 \
"

