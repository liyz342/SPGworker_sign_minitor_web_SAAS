DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker`  (
                                   `id` int(0) NOT NULL AUTO_INCREMENT,
                                   `workerId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `workerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `dateOfBirth` datetime(0) DEFAULT NULL,
                                   `identityNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `phoneNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `hireDate` datetime(0) DEFAULT NULL,
                                   `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   `healthStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `worker` (`workerId`, `workerName`, `gender`, `dateOfBirth`, `identityNumber`, `education`, `phoneNumber`, `address`, `hireDate`, `department`, `position`, `healthStatus`)
VALUES
    ('20240001', '工人1', '男', '1985-05-15 00:00:00', '123456789012345678', '大学', '13800000000', '北京市朝阳区', '2020-01-01 00:00:00', '工程部', '工程师', '良好'),
    ('20240002', '工人2', '女', '1990-08-22 00:00:00', '987654321098765432', '大专', '13911111111', '上海市浦东新区', '2018-06-15 00:00:00', '技术部', '技术支持', '良好'),
    ('20240003', '工人3', '男', '1978-11-30 00:00:00', '876543210123456789', '高中', '13722222222', '广州市天河区', '2015-03-10 00:00:00', '生产部', '操作工', '有慢性疾病');
