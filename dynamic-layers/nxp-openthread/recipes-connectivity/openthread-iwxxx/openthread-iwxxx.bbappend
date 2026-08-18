# Remove default RSSI setting, as it is not supported by WiFi firmware 18.99.3.p27.6
SRC_URI:remove = "\
    file://otpatches-095-a9a9d84/0036-set-default-rssi-on-reset-for-host.patch \
"
# i.MX9 SPI controller does not support MOSI idle level control (SPI_MOSI_IDLE_LOW).
SRC_URI:remove:mx9-nxp-bsp = "\
    file://otpatches-095-a9a9d84/0062-host-handle-power-save-mode-on-ssp-rxd.patch \
