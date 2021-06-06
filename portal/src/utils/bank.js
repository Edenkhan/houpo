const banks = [
  {
    key: "ICBC",
    hot: true,
    name: "中国工商银行"
  },
  {
    key: "ABC",
    hot: true,
    name: "中国农业银行"
  },
  {
    key: "CCB",
    hot: true,
    name: "中国建设银行"
  },
  {
    key: "CMB",
    hot: true,
    name: "招商银行"
  },
  {
    key: "BOC",
    hot: true,
    name: "中国银行"
  },
  {
    key: "PSBC",
    hot: true,
    name: "中国邮政储蓄银行"
  },
  {
    key: "COMM",
    hot: true,
    name: "交通银行"
  },
  {
    key: "CITIC",
    hot: true,
    name: "中信银行"
  },
  {
    key: "CMBC",
    hot: true,
    name: "中国民生银行"
  },
  {
    key: "CEB",
    hot: true,
    name: "中国光大银行"
  },
  {
    key: "CIB",
    hot: true,
    name: "兴业银行"
  },
  {
    key: "SPDB",
    hot: true,
    name: "上海浦东发展银行"
  },
  {
    key: "GDB",
    hot: true,
    name: "广东发展银行"
  },
  {
    key: "SPABANK",
    hot: true,
    name: "平安银行"
  },
  {
    key: "HXBANK",
    hot: true,
    name: "华夏银行"
  },
  {
    key: "BJBANK",
    hot: true,
    name: "北京银行"
  },
  {
    key: "SHBANK",
    hot: true,
    name: "上海银行"
  },
  {
    key: "JSBANK",
    hot: true,
    name: "江苏银行"
  },
  {
    key: "BJRCB",
    hot: true,
    name: "北京农村商业银行"
  },
  {
    key: "JSRCU",
    hot: true,
    name: "江苏省农村信用联合社"
  },
  {
    key: "TRCB",
    name: "天津农商银行"
  },
  {
    key: "SRCB",
    name: "深圳农村商业银行"
  },
  {
    key: "SHRCB",
    name: "上海农村商业银行"
  },
  {
    key: "CBBQS",
    name: "城市商业银行资金清算中心"
  },
  {
    key: "WHCCB",
    name: "威海市商业银行"
  },
  {
    key: "BOZK",
    name: "周口银行"
  },
  {
    key: "KORLABANK",
    name: "库尔勒市商业银行"
  },
  {
    key: "WJRCB",
    name: "吴江农商银行"
  },
  {
    key: "SDEB",
    name: "顺德农商银行"
  },
  {
    key: "HURCB",
    name: "湖北省农村信用社"
  },
  {
    key: "WRCB",
    name: "无锡农村商业银行"
  },
  {
    key: "BOCY",
    name: "朝阳银行"
  },
  {
    key: "CZBANK",
    name: "浙商银行"
  },
  {
    key: "HDBANK",
    name: "邯郸银行"
  },
  {
    key: "TCCB",
    name: "天津银行"
  },
  {
    key: "BOD",
    name: "东莞银行"
  },
  {
    key: "NCB",
    name: "南昌银行"
  },
  {
    key: "ZYCBANK",
    name: "遵义市商业银行"
  },
  {
    key: "SXCB",
    name: "绍兴银行"
  },
  {
    key: "GZRCU",
    name: "贵州省农村信用社"
  },
  {
    key: "ZJKCCB",
    name: "张家口市商业银行"
  },
  {
    key: "BOJZ",
    name: "锦州银行"
  },
  {
    key: "BOP",
    name: "平顶山银行"
  },
  {
    key: "HKB",
    name: "汉口银行"
  },
  {
    key: "SDRCU",
    name: "山东农信"
  },
  {
    key: "NXRCU",
    name: "宁夏黄河农村商业银行"
  },
  {
    key: "NYNB",
    name: "广东南粤银行"
  },
  {
    key: "GRCB",
    name: "广州农商银行"
  },
  {
    key: "BOSZ",
    name: "苏州银行"
  },
  {
    key: "HZCB",
    name: "杭州银行"
  },
  {
    key: "HSBK",
    name: "衡水银行"
  },
  {
    key: "HBC",
    name: "湖北银行"
  },
  {
    key: "JXBANK",
    name: "嘉兴银行"
  },
  {
    key: "HRXJB",
    name: "华融湘江银行"
  },
  {
    key: "BODD",
    name: "丹东银行"
  },
  {
    key: "AYCB",
    name: "安阳银行"
  },
  {
    key: "EGBANK",
    name: "恒丰银行"
  },
  {
    key: "CDB",
    name: "国家开发银行"
  },
  {
    key: "TCRCB",
    name: "江苏太仓农村商业银行"
  },
  {
    key: "NJCB",
    name: "南京银行"
  },
  {
    key: "ZZBANK",
    name: "郑州银行"
  },
  {
    key: "DYCB",
    name: "德阳商业银行"
  },
  {
    key: "YBCCB",
    name: "宜宾市商业银行"
  },
  {
    key: "SCRCU",
    name: "四川省农村信用"
  },
  {
    key: "KLB",
    name: "昆仑银行"
  },
  {
    key: "LSBANK",
    name: "莱商银行"
  },
  {
    key: "YDRCB",
    name: "尧都农商行"
  },
  {
    key: "CCQTGB",
    name: "重庆三峡银行"
  },
  {
    key: "FDB",
    name: "富滇银行"
  },
  {
    key: "BOCD",
    name: "承德银行"
  },
  {
    key: "JNBANK",
    name: "济宁银行"
  },
  {
    key: "LSBC",
    name: "临商银行"
  },
  {
    key: "JINCHB",
    name: "晋城银行JCBANK"
  },
  {
    key: "FXCB",
    name: "阜新银行"
  },
  {
    key: "WHRCB",
    name: "武汉农村商业银行"
  },
  {
    key: "HBYCBANK",
    name: "湖北银行宜昌分行"
  },
  {
    key: "TZCB",
    name: "台州银行"
  },
  {
    key: "TACCB",
    name: "泰安市商业银行"
  },
  {
    key: "XCYH",
    name: "许昌银行"
  },
  {
    key: "NBYZ",
    name: "鄞州银行"
  },
  {
    key: "NXBANK",
    name: "宁夏银行"
  },
  {
    key: "HSBANK",
    name: "徽商银行"
  },
  {
    key: "JJBANK",
    name: "九江银行"
  },
  {
    key: "NHQS",
    name: "农信银清算中心"
  },
  {
    key: "MTBANK",
    name: "浙江民泰商业银行"
  },
  {
    key: "LANGFB",
    name: "廊坊银行"
  },
  {
    key: "ASCB",
    name: "鞍山银行"
  },
  {
    key: "KSRB",
    name: "昆山农村商业银行"
  },
  {
    key: "YXCCB",
    name: "玉溪市商业银行"
  },
  {
    key: "DLB",
    name: "大连银行"
  },
  {
    key: "DRCBCL",
    name: "东莞农村商业银行"
  },
  {
    key: "GCB",
    name: "广州银行"
  },
  {
    key: "NBBANK",
    name: "宁波银行"
  },
  {
    key: "BOYK",
    name: "营口银行"
  },
  {
    key: "SXRCCU",
    name: "陕西信合"
  },
  {
    key: "GLBANK",
    name: "桂林银行"
  },
  {
    key: "BOQH",
    name: "青海银行"
  },
  {
    key: "CDRCB",
    name: "成都农商银行"
  },
  {
    key: "QDCCB",
    name: "青岛银行"
  },
  {
    key: "HKBEA",
    name: "东亚银行"
  },
  {
    key: "HBHSBANK",
    name: "湖北银行黄石分行"
  },
  {
    key: "WZCB",
    name: "温州银行"
  },
  {
    key: "BGB",
    name: "广西北部湾银行"
  },
  {
    key: "QLBANK",
    name: "齐鲁银行"
  },
  {
    key: "GDRCC",
    name: "广东省农村信用社联合社"
  },
  {
    key: "ZJTLCB",
    name: "浙江泰隆商业银行"
  },
  {
    key: "GZB",
    name: "赣州银行"
  },
  {
    key: "GYCB",
    name: "贵阳银行"
  },
  {
    key: "CQBANK",
    name: "重庆银行"
  },
  {
    key: "DAQINGB",
    name: "龙江银行"
  },
  {
    key: "CGNB",
    name: "南充市商业银行"
  },
  {
    key: "SCCB",
    name: "三门峡银行"
  },
  {
    key: "CSRCB",
    name: "常熟农村商业银行"
  },
  {
    key: "BHB",
    name: "河北银行"
  },
  {
    key: "JLBANK",
    name: "吉林银行"
  },
  {
    key: "CZRCB",
    name: "常州农村信用联社"
  },
  {
    key: "BANKWF",
    name: "潍坊银行"
  },
  {
    key: "ZRCBANK",
    name: "张家港农村商业银行"
  },
  {
    key: "FJHXBC",
    name: "福建海峡银行"
  },
  {
    key: "ZJNX",
    name: "浙江省农村信用社联合社"
  },
  {
    key: "LZYH",
    name: "兰州银行"
  },
  {
    key: "JSB",
    name: "晋商银行"
  },
  {
    key: "BOHAIB",
    name: "渤海银行"
  },
  {
    key: "CZCB",
    name: "浙江稠州商业银行"
  },
  {
    key: "YQCCB",
    name: "阳泉银行"
  },
  {
    key: "SJBANK",
    name: "盛京银行"
  },
  {
    key: "XABANK",
    name: "西安银行"
  },
  {
    key: "BSB",
    name: "包商银行"
  },
  {
    key: "JHBANK",
    name: "金华银行"
  },
  {
    key: "FSCB",
    name: "抚顺银行"
  },
  {
    key: "HNRCU",
    name: "河南省农村信用"
  },
  {
    key: "CSCB",
    name: "长沙银行"
  },
  {
    key: "XTB",
    name: "邢台银行"
  },
  {
    key: "XLBANK",
    name: "中山小榄村镇银行"
  },
  {
    key: "URMQCCB",
    name: "乌鲁木齐市商业银行"
  },
  {
    key: "HNRCC",
    name: "湖南省农村信用社"
  },
  {
    key: "DYCCB",
    name: "东营市商业银行"
  },
  {
    key: "ORBANK",
    name: "鄂尔多斯银行"
  },
  {
    key: "JLRCU",
    name: "吉林农信"
  },
  {
    key: "XYBANK",
    name: "信阳银行"
  },
  {
    key: "ZGCCB",
    name: "自贡市商业银行"
  },
  {
    key: "CDCB",
    name: "成都银行"
  },
  {
    key: "HANABANK",
    name: "韩亚银行"
  },
  {
    key: "LYCB",
    name: "辽阳市商业银行"
  },
  {
    key: "LYBANK",
    name: "洛阳银行"
  },
  {
    key: "GSRCU",
    name: "甘肃省农村信用"
  },
  {
    key: "ZBCB",
    name: "齐商银行"
  },
  {
    key: "CBKF",
    name: "开封市商业银行"
  },
  {
    key: "H3CB",
    name: "内蒙古银行"
  },
  {
    key: "ARCU",
    name: "安徽省农村信用社"
  },
  {
    key: "CRCBANK",
    name: "重庆农村商业银行"
  },
  {
    key: "SZSBK",
    name: "石嘴山银行"
  },
  {
    key: "DZBANK",
    name: "德州银行"
  },
  {
    key: "SRBANK",
    name: "上饶银行"
  },
  {
    key: "LSCCB",
    name: "乐山市商业银行"
  },
  {
    key: "JXRCU",
    name: "江西省农村信用"
  },
  {
    key: "BZMD",
    name: "驻马店银行"
  },
  {
    key: "JZBANK",
    name: "晋中市商业银行"
  },
  {
    key: "HZCCB",
    name: "湖州市商业银行"
  },
  {
    key: "NHB",
    name: "南海农村信用联社"
  },
  {
    key: "XXBANK",
    name: "新乡银行"
  },
  {
    key: "JRCB",
    name: "江苏江阴农村商业银行"
  },
  {
    key: "YNRCC",
    name: "云南省农村信用社"
  },
  {
    key: "GXRCU",
    name: "广西省农村信用"
  },
  {
    key: "HBRCU",
    name: "河北省农村信用社"
  },
  {
    key: "HKRCB",
    name: '海口农商银行'
  }
];

export function getBanks() {
  return banks;
}

export function getBank(key) {
  return banks.find(bank => bank.key === key);
}

