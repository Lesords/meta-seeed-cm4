SPLASH = "psplash-raspberrypi"

IMAGE_FEATURES:append = " \
	ssh-server-dropbear \
	splash \
	"

IMAGE_INSTALL:append = " \
	kernel-modules \
	seeed-linux-dtoverlays \
	ttf-dejavu-sans ttf-dejavu-sans-mono ttf-dejavu-sans-condensed \
	ttf-dejavu-serif ttf-dejavu-serif-condensed ttf-dejavu-common \
	python3-logging \
	python3-psutil \
	python3-evdev \
	evtest \
	lvgl-demo \
	iperf3 \
	i2c-tools \
	util-linux \
	e2fsprogs-resize2fs \
	parted \
	glibc \
	python3-pip \
	spidev-test \
	python3-seeed-python-reterminal \
    ${@bb.utils.contains('MACHINE', 'seeed-recomputer-r2x', 'r21-board-detect', '', d)} \
	"

