<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="供应商id" prop="supplierId">
        <el-input
          v-model="queryParams.supplierId"
          placeholder="请输入供应商id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入供应商名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在地区" prop="region">
        <el-select
          v-model="queryParams.region"
          placeholder="请选择所在地区"
          clearable
          @change="handleQuery"
        >
          <el-option
            v-for="item in regionOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="联系人姓名" prop="contactPerson">
        <el-input
          v-model="queryParams.contactPerson"
          placeholder="请输入联系人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactPhone">
        <el-input
          v-model="queryParams.contactPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mises:supplier:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['mises:supplier:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mises:supplier:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mises:supplier:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="supplierList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="供应商id" align="center" prop="supplierId" />
      <el-table-column label="供应商名称" align="center" prop="name" />
      <el-table-column label="所在地区" align="center" prop="region" />
      <el-table-column label="联系人姓名" align="center" prop="contactPerson" />
      <el-table-column label="联系电话" align="center" prop="contactPhone" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mises:supplier:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mises:supplier:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改供应商信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="供应商名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入供应商名称" />
        </el-form-item>
        <el-form-item label="所在地区" prop="region">
          <el-select
            v-model="form.region"
            placeholder="请选择所在地区"
            clearable
            @change="handleQuery"
          >
            <el-option
              v-for="item in regionOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="联系人姓名" prop="contactPerson">
          <el-input v-model="form.contactPerson" placeholder="请输入联系人姓名" />
        </el-form-item>
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSupplier, getSupplier, delSupplier, addSupplier, updateSupplier } from "@/api/mises/supplier";
import axios from 'axios';

export default {
  name: "Supplier",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 供应商信息表格数据
      supplierList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        supplierId: null,
        name: null,
        region: '',
        contactPerson: null,
        contactPhone: null,
        address: null,
        email: null
      },
      regionOptions: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "供应商名称不能为空", trigger: "blur" }
        ],
        region: [
          { required: true, message: "所在地区不能为空", trigger: "blur" }
        ],
        contactPerson: [
          { required: true, message: "联系人姓名不能为空", trigger: "blur" }
        ],
        contactPhone: [
          { required: true, message: "联系电话不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.fetchRegionData();
  },
  methods: {
    // 查询地区数据列表
    async fetchRegionData() {
      try {
        const response = await axios.get('/dev-api/mises/district/getAllDistrict');  // 替换为你的接口地址
        this.regionOptions  = response.data.data.map(item  => ({
          value: item.districtName,           // 使用 id 作为 value
          label: item.districtName  // 使用 districtName 作为 label
        }));
      } catch (error) {
        console.error(' 获取地区数据失败:', error);
      }
    },
    // 处理查询逻辑
    query() {
      console.log('Selected  region:', this.queryParams.region);
    },
    /** 查询供应商信息列表 */
    getList() {
      this.loading = true;
      listSupplier(this.queryParams).then(response => {
        this.supplierList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        supplierId: null,
        name: null,
        region: null,
        contactPerson: null,
        contactPhone: null,
        address: null,
        email: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.supplierId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加供应商信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const supplierId = row.supplierId || this.ids
      getSupplier(supplierId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改供应商信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.supplierId != null) {
            updateSupplier(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSupplier(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const supplierIds = row.supplierId || this.ids;
      this.$modal.confirm('是否确认删除供应商信息编号为"' + supplierIds + '"的数据项？').then(function() {
        return delSupplier(supplierIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mises/supplier/export', {
        ...this.queryParams
      }, `supplier_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
