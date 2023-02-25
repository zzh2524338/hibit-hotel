<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="name" label="Name"></el-table-column>
      <el-table-column prop="age" label="Age"></el-table-column>
      <el-table-column label="Actions">
        <template slot-scope="scope">
          <el-button type="danger" @click="deleteRow(scope.$index)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="primary" @click="addRow">Add Row</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          name: 'John',
          age: 22,
        },
        {
          name: 'Jane',
          age: 30,
        },
      ],
    };
  },
  methods: {
    addRow() {
      this.tableData.push({
        name: '',
        age: '',
      });
    },
    deleteRow(index) {
      this.tableData.splice(index, 1);
    },
  },
};
</script>
