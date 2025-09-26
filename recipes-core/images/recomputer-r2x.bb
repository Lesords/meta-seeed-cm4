include recipes-core/images/rpi-test-image.bb

SPLASH = "psplash-raspberrypi"

IMAGE_FEATURES:append = " \
    ssh-server-openssh \
    tools-sdk \
	splash \
	"

IMAGE_INSTALL:append = " \
	kernel-modules \
	seeed-linux-dtoverlays \
	python3-logging \
	python3-psutil \
	python3-evdev \
	evtest \
	iperf3 \
	i2c-tools \
	util-linux \
	e2fsprogs-resize2fs \
	parted \
	glibc \
	python3-pip \
    python3-pygobject \
    python3-pycairo \
	spidev-test \
	python3-seeed-python-reterminal \
    openssh \
    openssh-sshd \
    openssh-scp \
    protobuf \
    protobuf-compiler \
    opencv-dev \
    opencv-staticdev \
    recomputer-r2x-detection \
    raspi-utils \
    can-utils \
    watchdog \
    minicom \
    git \
    cmake \
    make \
    g++ \
    gcc \
    libstdc++ \
    tmux \
    vim \
    dpkg \
    pkgconfig \
    wget \
    curl \
    hailo-firmware \
    libhailort-dev \
    hailortcli \
	"

