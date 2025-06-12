/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost
 Source Server Type    : MySQL
 Source Server Version : 80019 (8.0.19)
 Source Host           : localhost:3306
 Source Schema         : mises

 Target Server Type    : MySQL
 Target Server Version : 80019 (8.0.19)
 File Encoding         : 65001

 Date: 09/03/2025 11:52:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '分类唯一标识',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称（如服装、食品）',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品分类表（支持树形结构）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int NOT NULL AUTO_INCREMENT COMMENT '客户唯一标识',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户名称',
  `region` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所在地区',
  `contact_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `product_id` int NOT NULL COMMENT '关联商品ID',
  `quantity` int NOT NULL COMMENT '当前库存量',
  `last_updated` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`product_id`) USING BTREE,
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `inventory_chk_1` CHECK (`quantity` >= 0)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实时库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT COMMENT '商品唯一标识',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `category_id` int NOT NULL COMMENT '所属分类ID',
  `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位（如件、千克）',
  `specification` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格（如颜色、尺寸）',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '商品描述',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `CategoryID`(`category_id` ASC) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE `purchase_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '采购订单唯一标识',
  `supplier_id` int NOT NULL COMMENT '关联供应商ID',
  `order_date` datetime NOT NULL COMMENT '下单时间',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单总金额',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `SupplierID`(`supplier_id` ASC) USING BTREE,
  CONSTRAINT `purchase_order_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购订单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for purchase_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order_detail`;
CREATE TABLE `purchase_order_detail`  (
  `detail_id` int NOT NULL AUTO_INCREMENT COMMENT '明细唯一标识',
  `order_id` int NOT NULL COMMENT '关联采购订单ID',
  `product_id` int NOT NULL COMMENT '关联商品ID',
  `quantity` int NOT NULL COMMENT '采购数量',
  `unit_price` decimal(10, 2) NOT NULL COMMENT '采购单价（历史记录）',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `OrderID`(`order_id` ASC) USING BTREE,
  INDEX `ProductID`(`product_id` ASC) USING BTREE,
  CONSTRAINT `purchase_order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `purchase_order` (`order_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `purchase_order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购订单明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sales_order
-- ----------------------------
DROP TABLE IF EXISTS `sales_order`;
CREATE TABLE `sales_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '销售订单唯一标识',
  `customer_id` int NOT NULL COMMENT '关联客户ID',
  `order_date` datetime NOT NULL COMMENT '下单时间',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单总金额',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `CustomerID`(`customer_id` ASC) USING BTREE,
  CONSTRAINT `sales_order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售订单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sales_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `sales_order_detail`;
CREATE TABLE `sales_order_detail`  (
  `detail_id` int NOT NULL AUTO_INCREMENT COMMENT '明细唯一标识',
  `order_id` int NOT NULL COMMENT '关联销售订单ID',
  `product_id` int NOT NULL COMMENT '关联商品ID',
  `quantity` int NOT NULL COMMENT '销售数量',
  `unit_price` decimal(10, 2) NOT NULL COMMENT '销售单价（历史记录）',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `OrderID`(`order_id` ASC) USING BTREE,
  INDEX `ProductID`(`product_id` ASC) USING BTREE,
  CONSTRAINT `sales_order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `sales_order` (`order_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sales_order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售订单明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int NOT NULL AUTO_INCREMENT COMMENT '供应商唯一标识',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '供应商名称',
  `region` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所在地区',
  `contact_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`supplier_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '供应商信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;