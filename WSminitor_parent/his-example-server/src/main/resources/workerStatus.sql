DROP TABLE IF EXISTS `workers_status`;
CREATE TABLE `workers_status`  (
                                   `statusId` int(0) NOT NULL AUTO_INCREMENT,
                                   `deviceId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `workerId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `workerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `statusDate` datetime(0) DEFAULT NULL,
                                   `heartRate` int(0) DEFAULT NULL,
                                   `DBP` int(0) DEFAULT NULL,
                                   `SBP` int(0) DEFAULT NULL,
                                   `SaO2` int(0) DEFAULT NULL,
                                   `temperature` double DEFAULT NULL,
                                   `status` int(0) DEFAULT NULL,
                                   PRIMARY KEY (`statusId`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 插入数据的语句，这里省略了statusId列，让它自动递增
INSERT INTO `workers_status` (`deviceId`, `workerId`, `workerName`, `statusDate`, `heartRate`, `DBP`, `SBP`, `SaO2`, `temperature`, `status`) VALUES
                                                                                                                                                  ('00001','20240001','工人1', '2023-11-21 15:13:09', 70, 70, 100, 96, 36.5, 0),
                                                                                                                                                  ('00001','20240002','工人2', '2023-11-21 15:13:09', 75, 70, 100, 96, 36.5, 0),
                                                                                                                                                  ('00003','20240003','工人3', '2023-11-21 15:13:09', 77, 70, 100, 96, 36.5, 0),
                                                                                                                                                  ('00001','20240004','工人4', '2023-11-21 15:13:09', 100, 100, 150, 96, 36.6, 1);