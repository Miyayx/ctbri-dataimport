DROP TABLE IF EXISTS `autohome`.`auto`;
CREATE TABLE `autohome`.`auto` (
      `id` INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
      `page_id` INTEGER NOT NULL,
      `url` VARCHAR(1000) NOT NULL,
      `price` FLOAT NOT NULL,
      `score` FLOAT NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE INDEX `idx_auto__id` ON `auto`(`page_id`);

CREATE TABLE `autohome`.`auto_basic` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `厂商` VARCHAR(50) NOT NULL,
      `级别` VARCHAR(50) NOT NULL,
      `发动机` VARCHAR(50) NOT NULL,
      `变速箱` VARCHAR(50) NOT NULL,
      `长宽高` VARCHAR(50) NOT NULL,
      `车身结构` VARCHAR(50) NOT NULL,
      `最高车速` FLOAT NOT NULL,
      `实测油耗` FLOAT NOT NULL,
      `工信部综合油耗` FLOAT NOT NULL,
      `实测离地间隙` FLOAT NOT NULL,
      `整车质保` VARCHAR(50) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE INDEX `idx_auto_basic__auto_id` ON `auto_basic` (`auto_id`);

ALTER TABLE `autohome`.`auto_basic` ADD CONSTRAINT `fk_auto_basic__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_carbody` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `长度` INTEGER NOT NULL,
      `宽度` INTEGER NOT NULL,
      `高度` INTEGER NOT NULL,
      `轴距` INTEGER NOT NULL,
      `前轮距` INTEGER NOT NULL,
      `后轮距` INTEGER NOT NULL,
      `最小离地间隙` INTEGER NOT NULL,
      `整备质量` INTEGER NOT NULL,
      `车身结构` INTEGER NOT NULL,
      `车门数` INTEGER NOT NULL,
      `座位数` INTEGER NOT NULL,
      `油箱容积` INTEGER NOT NULL,
      `行李厢容积` VARCHAR(80) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_carbody` ADD CONSTRAINT `fk_auto_carbody__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_engine` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `发动机型号` VARCHAR(100) NOT NULL,
      `排量` INTEGER(10) NOT NULL,
      `进气形式` VARCHAR(50) NOT NULL,
      `气缸排列形式` VARCHAR(20) NOT NULL,
      `气缸数` INTEGER(3) NOT NULL,
      `每缸气门数` INTEGER(3) NOT NULL,
      `压缩比` FLOAT NOT NULL,
      `配气机构` VARCHAR(50) NOT NULL,
      `缸径` INTEGER(10) NOT NULL,
      `行程` FLOAT NOT NULL,
      `最大马力` INTEGER(10) NOT NULL,
      `最大功率` INTEGER(10) NOT NULL,
      `最大功率转速` INTEGER(10) NOT NULL,
      `最大扭矩` INTEGER(10) NOT NULL,
      `最大扭矩转速` INTEGER(10) NOT NULL,
      `发动机特有技术` VARCHAR(50) NOT NULL,
      `燃料形式` VARCHAR(50) NOT NULL,
      `燃油标号` VARCHAR(50) NOT NULL,
      `供油方式` VARCHAR(50) NOT NULL,
      `缸盖材料` VARCHAR(50) NOT NULL,
      `缸体材料` VARCHAR(50) NOT NULL,
      `环保标准` VARCHAR(50) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_engine` ADD CONSTRAINT `fk_auto_engine__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_gearbox` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `简称` VARCHAR(50) NOT NULL,
      `挡位个数` INTEGER(3) NOT NULL,
      `变速箱类型` VARCHAR(50) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_gearbox` ADD CONSTRAINT `fk_auto_gearbox__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_chassis` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `驱动方式` VARCHAR(50) NOT NULL,
      `前悬架类型` VARCHAR(50) NOT NULL,
      `后悬架类型` VARCHAR(50) NOT NULL,
      `助力类型` VARCHAR(50) NOT NULL,
      `车体结构` VARCHAR(50) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_chassis` ADD CONSTRAINT `fk_auto_chassis__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_WheelBrake` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `前制动器类型` VARCHAR(50) NOT NULL,
      `后制动器类型` VARCHAR(50) NOT NULL,
      `驻车制动类型` VARCHAR(50) NOT NULL,
      `前轮胎规格` VARCHAR(50) NOT NULL,
      `后轮胎规格` VARCHAR(50) NOT NULL,
      `备胎规格` VARCHAR(50) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_WheelBrake` ADD CONSTRAINT `fk_auto_WheelBrake__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_SafetyEquipment` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `主驾驶座安全气囊` INTEGER(2) NOT NULL,
      `副驾驶座安全气囊` INTEGER(2) NOT NULL,
      `前排侧气囊` INTEGER(2) NOT NULL,
      `后排侧气囊` INTEGER(2) NOT NULL,
      `前排头部气囊` INTEGER(2) NOT NULL,
      `后排头部气囊` INTEGER(2) NOT NULL,
      `膝部气囊` INTEGER(2) NOT NULL,
      `胎压监测装置` INTEGER(2) NOT NULL,
      `零胎压继续行驶` INTEGER(2) NOT NULL,
      `安全带未系提示` INTEGER(2) NOT NULL,
      `ISOFIX儿童座椅接口` INTEGER(2) NOT NULL,
      `发动机电子防盗` INTEGER(2) NOT NULL,
      `车内中控锁` INTEGER(2) NOT NULL,
      `遥控钥匙` INTEGER(2) NOT NULL,
      `无钥匙启动系统` INTEGER(2) NOT NULL,
      `无钥匙进入系统` INTEGER(2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_SafetyEquipment` ADD CONSTRAINT `fk_auto_SafetyEquipment__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_ControlConfig` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `ABS防抱死` INTEGER(2) NOT NULL,
      `制动力分配` INTEGER(2) NOT NULL,
      `刹车辅助` INTEGER(2) NOT NULL,
      `牵引力控制` INTEGER(2) NOT NULL,
      `车身稳定控制` INTEGER(2) NOT NULL,
      `上坡辅助` INTEGER(2) NOT NULL,
      `自动驻车` INTEGER(2) NOT NULL,
      `陡坡缓降` INTEGER(2) NOT NULL,
      `可变悬架` INTEGER(2) NOT NULL,
      `空气悬架` INTEGER(2) NOT NULL,
      `可变转向比` INTEGER(2) NOT NULL,
      `前桥限滑差速器/差速锁` INTEGER(2) NOT NULL,
      `中央差速器锁止功能` INTEGER(2) NOT NULL,
      `后桥限滑差速器/差速锁` INTEGER(2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_ControlConfig` ADD CONSTRAINT `fk_auto_ControlConfig__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_ExternalConfig` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `电动天窗` INTEGER(2) NOT NULL,
      `全景天窗` INTEGER(2) NOT NULL,
      `运动外观套件` INTEGER(2) NOT NULL,
      `铝合金轮圈` INTEGER(2) NOT NULL,
      `电动吸合门` INTEGER(2) NOT NULL,
      `侧滑门` INTEGER(2) NOT NULL,
      `电动后备厢` INTEGER(2) NOT NULL,
      `感应后备厢` INTEGER(2) NOT NULL,
      `车顶行李架` INTEGER(2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_ExternalConfig` ADD CONSTRAINT `fk_auto_ExternalConfig__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_InternalConfig` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `真皮方向盘` INTEGER(2) NOT NULL,
      `方向盘调节` VARCHAR(50) NOT NULL,
      `方向盘电动调节` INTEGER(2) NOT NULL,
      `多功能方向盘` INTEGER(2) NOT NULL,
      `方向盘换挡` INTEGER(2) NOT NULL,
      `方向盘加热` INTEGER(2) NOT NULL,
      `方向盘记忆` INTEGER(2) NOT NULL,
      `定速巡航` INTEGER(2) NOT NULL,
      `前驻车雷达` INTEGER(2) NOT NULL,
      `后驻车雷达` INTEGER(2) NOT NULL,
      `倒车视频影像` INTEGER(2) NOT NULL,
      `行车电脑显示屏` INTEGER(2) NOT NULL,
      `全液晶仪表盘` INTEGER(2) NOT NULL,
      `HUD抬头数字显示` INTEGER(2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_InternalConfig` ADD CONSTRAINT `fk_auto_InternalConfig__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_SeatConfig` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `座椅材质` VARCHAR(50) NOT NULL,
      `运动风格座椅` INTEGER(2) NOT NULL,
      `座椅高低调节` INTEGER(2) NOT NULL,
      `腰部支撑调节` INTEGER(2) NOT NULL,
      `肩部支撑调节` INTEGER(2) NOT NULL,
      `主驾驶座电动调节` INTEGER(2) NOT NULL,
      `副驾驶座电动调节` INTEGER(2) NOT NULL,
      `第二排靠背角度调节` INTEGER(2) NOT NULL,
      `第二排座椅移动` INTEGER(2) NOT NULL,
      `后排座椅电动调节` INTEGER(2) NOT NULL,
      `电动座椅记忆` INTEGER(2) NOT NULL,
      `前排座椅加热` INTEGER(2) NOT NULL,
      `后排座椅加热` INTEGER(2) NOT NULL,
      `前排座椅通风` INTEGER(2) NOT NULL,
      `后排座椅通风` INTEGER(2) NOT NULL,
      `前排座椅按摩` INTEGER(2) NOT NULL,
      `后排座椅按摩` INTEGER(2) NOT NULL,
      `第三排座椅` INTEGER(2) NOT NULL,
      `后排座椅放倒方式` INTEGER(2) NOT NULL,
      `前中央扶手` INTEGER(2) NOT NULL,
      `后中央扶手` INTEGER(2) NOT NULL,
      `后排杯架` INTEGER(2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_SeatConfig` ADD CONSTRAINT `fk_auto_SeatConfig__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_MediaConfig` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `GPS导航系统` INTEGER(2) NOT NULL,
      `定位互动服务` INTEGER(2) NOT NULL,
      `中控台彩色大屏` INTEGER(2) NOT NULL,
      `蓝牙/车载电话` INTEGER(2) NOT NULL,
      `车载电视` INTEGER(2) NOT NULL,
      `后排液晶屏` INTEGER(2) NOT NULL,
      `220V/230V电源` INTEGER(2) NOT NULL,
      `外接音源接口` VARCHAR(50) NOT NULL,
      `CD支持MP3/WMA` INTEGER(2) NOT NULL,
      `多媒体系统` VARCHAR(50) NOT NULL,
      `扬声器品牌` VARCHAR(50) NOT NULL,
      `扬声器数量` VARCHAR(50) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_MediaConfig` ADD CONSTRAINT `fk_auto_MediaConfig__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_LightConfig` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `近光灯` VARCHAR(50) NOT NULL,
      `远光灯` VARCHAR(50) NOT NULL,
      `日间行车灯` INTEGER(2) NOT NULL,
      `自适应远近光` INTEGER(2) NOT NULL,
      `自动头灯` INTEGER(2) NOT NULL,
      `转向辅助灯` INTEGER(2) NOT NULL,
      `转向头灯` INTEGER(2) NOT NULL,
      `前雾灯` INTEGER(2) NOT NULL,
      `大灯高度可调` INTEGER(2) NOT NULL,
      `大灯清洗装置` INTEGER(2) NOT NULL,
      `车内氛围灯` INTEGER(2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_LightConfig` ADD CONSTRAINT `fk_auto_LightConfig__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_GlassConfig` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `前电动车窗` INTEGER(2) NOT NULL,
      `后电动车窗` INTEGER(2) NOT NULL,
      `车窗防夹手功能` INTEGER(2) NOT NULL,
      `防紫外线/隔热玻璃` INTEGER(2) NOT NULL,
      `后视镜电动调节` INTEGER(2) NOT NULL,
      `后视镜加热` INTEGER(2) NOT NULL,
      `内后视镜自动防眩目` INTEGER(2) NOT NULL,
      `外后视镜自动防眩目` INTEGER(2) NOT NULL,
      `后视镜电动折叠` INTEGER(2) NOT NULL,
      `后视镜记忆` INTEGER(2) NOT NULL,
      `后风挡遮阳帘` INTEGER(2) NOT NULL,
      `后排侧遮阳帘` INTEGER(2) NOT NULL,
      `后排侧隐私玻璃` INTEGER(2) NOT NULL,
      `遮阳板化妆镜` INTEGER(2) NOT NULL,
      `后雨刷` INTEGER(2) NOT NULL,
      `感应雨刷` INTEGER(2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_GlassConfig` ADD CONSTRAINT `fk_auto_GlassConfig__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_Aircon` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `空调控制方式` VARCHAR(20) NOT NULL,
      `后排独立空调` INTEGER(2) NOT NULL,
      `后座出风口` INTEGER(2) NOT NULL,
      `温度分区控制` INTEGER(2) NOT NULL,
      `车内空气调节/花粉过滤` INTEGER(2) NOT NULL,
      `车载冰箱` INTEGER(2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_Aircon` ADD CONSTRAINT `fk_auto_Aircon__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);

CREATE TABLE `autohome`.`auto_HighTechConfig` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `auto_id` INTEGER NOT NULL,
      `自动泊车入位` INTEGER(2) NOT NULL,
      `发动机启停技术` INTEGER(2) NOT NULL,
      `并线辅助` INTEGER(2) NOT NULL,
      `车道偏离预警系统` INTEGER(2) NOT NULL,
      `主动刹车/主动安全系统` INTEGER(2) NOT NULL,
      `整体主动转向系统` INTEGER(2) NOT NULL,
      `夜视系统` INTEGER(2) NOT NULL,
      `中控液晶屏分屏显示` INTEGER(2) NOT NULL,
      `自适应巡航` INTEGER(2) NOT NULL,
      `全景摄像头` INTEGER(2) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `autohome`.`auto_HighTechConfig` ADD CONSTRAINT `fk_auto_HighTechConfig__auto_id` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`);
