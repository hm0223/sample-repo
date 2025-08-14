package com.hm.feature.toogle;


public class InvestmentCalculator {
    /**
     * 计算投资盈利率或亏损率
     * @param initialInvestment 初始投资金额
     * @param currentValue 当前价值
     * @return 收益率百分比（正数为盈利，负数为亏损）
     * @throws IllegalArgumentException 初始投资金额不能为0
     */
    public static double calculateRate(double initialInvestment, double currentValue) {
        if (initialInvestment == 0) {
            throw new IllegalArgumentException("初始投资金额不能为0");
        }
        return ((currentValue - initialInvestment) / initialInvestment) * 100;
    }

    public static void main(String[] args) {
        double initial = 222578.19 + 55067.75;  // 初始投资 xx 元
        double current = 222578.19;                // 当前价值 xx 元
        
        double rate = calculateRate(initial, current);
        System.out.printf("收益率：%.2f%%%n", rate);  // 输出：收益率：xx.00%
        
        // 测试亏损场景  
        initial = 47268.19 + 24331.81;
        current = 47268.19;
        rate = calculateRate(initial, current);
        System.out.printf("收益率：%.2f%%%n", rate);  // 输出：收益率：-xx
        
         // 测试亏损场景  
        initial = 4133.90 + 1050.46;
        current = 4133.90;
        rate = calculateRate(initial, current);
        System.out.printf("收益率：%.2f%%%n", rate);  // 输出：收益率：-xx.00%

          // 测试亏损场景  
        initial = 1681.20 + 47.84;
        current = 1681.20;
        rate = calculateRate(initial, current);
        System.out.printf("收益率：%.2f%%%n", rate);  // 输出：收益率：-xx.00%

        // 319.38
        // 61.30
    }
}

