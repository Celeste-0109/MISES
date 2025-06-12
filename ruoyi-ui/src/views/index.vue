<template>
  <!-- 唯一根元素：将所有内容包裹在单一容器内 -->
  <div class="root-container">
    <!-- 原 .app-container.home  部分 -->
    <div class="app-container home">
      <el-row :gutter="20">
          <p style="font-size: 36px; text-align: center;">企业销售管理系统</p>
      </el-row>
    </div>

    <!-- 图表容器部分 -->
    <div class="chart-wrapper">
      <div ref="chart" style="width: 800px; height: 500px"></div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import Vue from 'vue';
import axios from 'axios';
const http = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 10000
})
Vue.prototype.$http = http
export default {
  mounted() {
    this.$nextTick(() => { // 确保DOM渲染完成
      console.log('开始初始化图表');
      this.initChart();
      console.log('图表初始化完成');
      this.loadData();
      window.addEventListener('resize', () => {
        this.chart?.resize();  // 响应式调整
      });
    });
  },
  beforeDestroy() {
    // 销毁时释放资源
    if (this.chart) {
      this.chart.dispose();
      this.chart = null;
    }
    window.removeEventListener('resize', this.handleResize);
  },
  methods: {
    initChart() {
      if (!this.$refs.chart) {
        console.error('图表 DOM 元素未找到');
        return;
      }
      this.chart = echarts.init(this.$refs.chart);
      if (!this.chart) {
        console.error('ECharts 初始化失败');
        return;
      }
      this.chart.setOption({
        title: {},
        tooltip: {},
        xAxis: {
          type: 'category',
          axisLabel: {
            interval: 0,
            rotate: 45, // 标签旋转防重叠
            //formatter: value => value.substring(0, 6) // 长文本截断
          }
        },
        yAxis: {
          type: 'value',
          axisLabel: { formatter: '{value} 个' }
        },
        series: [{ // 必须声明类型
          type: 'bar',
          barWidth: '60%', // 柱宽控制
          itemStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              { offset: 0, color: '#83bff6' },
              { offset: 1, color: '#188df0' }
            ])
          }
        }]
      });
    },

    async loadData() {
      try {
        console.log('开始加载数据');
        const res = await this.$http.get('/mises/inventory/byCategory');
        console.log('原始数据:', res.data);  // 关键调试点

        // 提取 data 字段
        const rawData = res.data.data || [];

        // 数据字段兼容性处理
        const sourceData = rawData.map(item => ({
          productName: String(item.productName || '未命名'),
          quantity: Number(item.quantity || 0)
        }));
        console.log('处理后数据:', sourceData); // 验证数据结构

        if (sourceData.length === 0) {
          console.error('处理后的数据为空，检查原始数据格式和字段名');
          return;
        }

        // 更新图表选项
        this.chart.setOption({
          xAxis: {
            data: sourceData.map(item => item.productName)
          },
          series: [{
            data: sourceData.map(item => item.quantity)
          }]
        });
        console.log('图表数据更新完成');
      } catch (error) {
        console.error('数据加载失败:', error);
      }
    }
  }
};
</script>
