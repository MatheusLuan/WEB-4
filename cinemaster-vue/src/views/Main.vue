<template>
  <div>
    <div class="row mt-4 mb-4">
      <div v-if="objInfo" class="card">
        <h2 class="card-header">Filme details</h2>
        <button
          @click="closeDetails"
          style="width: 110px"
          type="button"
          class="btn btn-secondary btn-block"
        >close</button>
        <form>
          <div class="single category">            
            
              <label class="col-sm-2 col-form-label">Poster</label>
              <div class="col-sm-2">
                <span class="form-control"><img width="100" :src="objInfo.moviedb['poster_path']"></span>
              </div>

              <label class="col-sm-2 col-form-label">Titulo</label>
              <div class="col-sm-10">
                <span class="form-control">{{objInfo.moviedb['title']}}</span>
              </div> 

              <label class="col-sm-2 col-form-label">Lançamento</label>
              <div class="col-sm-10">
                <span class="form-control">{{objInfo.lancamento}}</span>
              </div> 

               <label class="col-sm-2 col-form-label">Nota</label>
              <div class="col-sm-10">
                <span class="form-control">{{objInfo.nota}}</span>
              </div> 
              

              <label class="col-sm-2 col-form-label">Categorias</label>
              <div class="col-sm-10">
                <span class="form-control">

                  <span 
                  v-for="categoria in objInfo.categorias"
                  :key="categoria.id">
                   |
                    {{" " + categoria + " "}}
                   |
                  </span>

                </span>
              </div>  

              <label class="col-sm-2 col-form-label">Pessoas</label>
              <div class="col-sm-10">
                <span class="form-control">

                  <span 
                  v-for="pessoa in objInfo.pessoas"
                  :key="pessoa.id">
                  |
                    {{" " + pessoa.nome + " "}}
                  |
                  </span>
                </span>
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
              <th data-sortable="true" data-field="categoria">Categoria</th>
              <th data-sortable="true" data-field="tipo">Tipo</th>
              <th @click="orderByNota" class='run' data-sortable="true">Nota &#8650;</th>
              <th data-sortable="true">#</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in filmes" :key="item.id">
              <td>{{ item.id }}</td>
              <td
                style="color: blue; cursor: pointer"
                @click="getObjInfo(item.id)"
                title="details"
              >
                {{ item.nome }}
              </td>
              
              <td> 
                <span 
                  v-for="categoria in item.categorias"
                  :key="categoria.id">
                   |
                    {{" " + categoria + " "}}
                   |
                  </span>
             </td>
              <td>{{ item.tipo }}</td>
              <td>{{ item.nota }}</td>
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
  name: "Filmes",
  components: {},
  data() {
    return {
      index: 0,
      filmes: [],
      renderTable: true,
      objInfo: null,
    };
  },
  methods: {
    closeDetails: function () {
      this.renderTable = true;
      this.objInfo = null;
    },
    remove(objId){
      const delete_url = `http://localhost:8080/api/filme/${objId}`;
      axios.delete(delete_url).then((res) => {
        this.getLista();
      });
    },
    getLista() {
      const path = "http://localhost:8080/api/filmes";
      axios.get(path).then((res) => {
        this.filmes = res.data;
      });
    },
    orderByNota() {
      const path = "http://localhost:8080/api/filmes?order=nota";
      axios.get(path).then((res) => {
        this.filmes = res.data;
      });
    },
    getObjInfo: function (id) {
      const api_filme_url = `http://localhost:8080/api/filme/${id}`;
      this.renderTable = false;
      axios.get(api_filme_url).then((response) => {
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