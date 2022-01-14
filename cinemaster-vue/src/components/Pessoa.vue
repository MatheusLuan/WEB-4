<template>
  <div>
    <div class="row mt-4 mb-4">
      <div v-if="objInfo" class="card">
        <h2 class="card-header">Pessoa details</h2>
        <button
          @click="closeDetails"
          style="width: 110px"
          type="button"
          class="btn btn-secondary btn-block"
        >close</button>
        <form>
          <div class="single category">          
            
              <label class="col-sm-2 col-form-label">Foto</label>
              <div class="col-sm-10">
                <span class="form-control"><img width="100" :src="objInfo.persondb['profile_path']"></span>
              </div> 

              <label class="col-sm-2 col-form-label">Nome</label>
              <div class="col-sm-10">
                <span class="form-control">{{objInfo.nome}}</span>
              </div>

              <label class="col-sm-2 col-form-label">Data nascimento</label>
              <div class="col-sm-10">
                <span class="form-control">{{objInfo.dataNasc}}</span>
              </div>  
              
              <label class="col-sm-2 col-form-label">Popularidade</label>
              <div class="col-sm-10">
                <span class="form-control">{{objInfo.persondb['popularity']}}</span>
              </div>    

              <label class="col-sm-2 col-form-label">Biografia</label>
              <div class="col-sm-10">
                <span class="form-control">{{objInfo.persondb['biography']}}</span>
              </div>       
          </div>
        </form>
      </div>

      <div class="col-md-12 text-center">
        <table
          class="table table-striped table-bordered table-hover"
          data-toggle="table"
        >
          <thead>
            <tr>
              <th data-sortable="true" data-field="id">ID</th>
              <th data-sortable="true" data-field="nome">Nome</th>
              <th data-sortable="true" data-field="dataNasc">Nascimento</th>
              <th data-sortable="true" data-field="age">Idade</th>     
              <th data-sortable="true">#</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in lista" :key="item.id">
              <td>{{ item.id }}</td>
              <td
                style="color: blue; cursor: pointer"
                @click="getObjInfo(item.id)"
                title="details"
              >
                {{ item.nome }}
              </td>              
              <td>{{ item.dataNasc }}</td>
              <td>{{ item.age }}</td>
              <td>
                <button @click="remove(item.id)">&#x2715;</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Pessoa",
  components: {},
  data() {
    return {
      index: 0,
      lista: [],
      renderTable: true,
      objInfo: null,
    };
  },
  methods: {
    closeDetails: function () {
      this.renderTable = true;
      this.objInfo = null;
    },
    /* operacoes de delete devem ser autorizadas, exemplo:
    axios.delete(url, {data:{username:"user", password:"pass"}, headers:{Authorization: "token"}})
    */
    remove(objId){
      const delete_url = `http://localhost:8080/api/pessoa/${objId}`;
      axios.delete(delete_url).then((res) => {
        this.getLista();
      });
    },
    getLista() {
      const list_url = "http://localhost:8080/api/pessoas";
      axios.get(list_url).then((res) => {
        this.lista = res.data;
      });
    },
    getObjInfo: function (objId) {
      const get_url = `http://localhost:8080/api/pessoa/${objId}`;
      this.renderTable = false;
      axios.get(get_url).then((response) => {
        this.objInfo = response.data;
      });
    },
  },
  created: function () {
    this.getLista();
  },
};
</script>

<style scoped>
.card{
    border: none;
}
.single {
  padding: 30px 15px;

  background: #fcfcfc;
  border: 1px solid #f0f0f0;
}
.single h3.side-title {
  margin: 0;
  margin-bottom: 10px;
  padding: 0;
  font-size: 20px;
  color: #333;
  text-transform: uppercase;
}
.single h3.side-title:after {
  content: "";
  width: 60px;
  height: 1px;
  background: #ff173c;
  display: block;
  margin-top: 6px;
}

.single ul {
  margin-bottom: 0;
}
.single li a {
  color: #666;
  font-size: 14px;
  text-transform: uppercase;
  border-bottom: 1px solid #f0f0f0;
  line-height: 40px;
  display: block;
  text-decoration: none;
}
.single li a:hover {
  color: #ff173c;
}
.single li:last-child a {
  border-bottom: 0;
}
</style>