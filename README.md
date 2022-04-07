# pokedex-egsys

## Bibliotecas Utilizadas:
* Retrofit com Gson
* Glide
* LifeCycle - Viewmodel

## API Consumida:
* https://pokeapi.co

## Padrão utilizado:
* MVVM

## Requisitos Implementados

#### SplashScreen (ConstraintLayout)
* logomarca pokedex
* logotipo pokedex
* progressbar de carregamento

#### Screen secundária com Recurso VideoView (ConstraintLayout)
* Quem é esse pokemon?

#### Home (LinearLayout)
* RecyclerView para listagem dos pokemons
* CardView para carregar  nome, imagem e número do pokemon
* Floating Action Button para gerar pokemons aleatórios
* SearchViewBar para filtragens de pokemons

#### Tela de Decrição (ConstraintLayout)
* Floating Action Button para gerar pokemons aleatórios
* Nome
* Imagem
* Altura
* Peso


# Código Comentado:

## SplashScreen:

    supportActionBar?.hide() // Esconde a actionbar no carregamento da tela

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN //coloca a tela de abertura em modo tela cheia
        )

        Handler().postDelayed({
            val intent = Intent(this, PokeWhoActivity::class.java) //faz a chamada para a tela do "Quem é esse pokemon?", e desempilha a activity atual
            startActivity(intent)
            finish()
        }, 7000) // duração da tela de abertura de 7 segundos



## PokeWhoScreeen:


        supportActionBar?.hide() // Esconde a actionbar no carregamento da tela

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN //coloca a tela de abertura em modo tela cheia
        )
        
        val videoView = findViewById<VideoView>(R.id.videoView)//recupera o recurso VideoView
        
        //Função para gerar um número aleatório entre 1 e 10, que será passado 
        //como parâmetro para o ->When fazendo a escolha randomica do video
        fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) + start

        when ((1..10).random()) {
            1 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.bulbasaur)//recupera o caminho do arquivo do video
            }
            2 -> {videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.caterpie)
            }
           .
           .
           10// lista de arquivos
            }
        }

        videoView.start()// inicia o video automaticamente, sem recursos de controle de  mídea

        Handler().postDelayed({
            startActivity(Intent(this, PokeListActivity::class.java))//Ao finalizar, chama a Activity POkeList e desempilha a activity atual
            finish()
        }, 12000)// ruração média do vide é de 12 segundos
        
  ## PokeListActivity
  
    supportActionBar?.title = "Pokedex" // Configura o título da ActionBar
    
        )//recupera o recurso Floating ACtion Button
        val fabRandom = findViewById<FloatingActionButton>(R.id.fabRandom
        
        fabRandom.setOnClickListener {
        
              //chama o método que gera pokemons aleatórios
              randomPokemon() 
        }
        
        //recupera a lista de pokemons acessadas pelo retrofit
        viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)
        
        //método que chama a função para incluir coloca os itens do adapter na recyclerView
        initUI()
    }
    
    
    //Função para gerar pokemons aleatórios
    private fun randomPokemon() {
        fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) + start
        val pokeRandom = (1..151).random() //gera pokemons do primeiro até o último da listagem
        val intent = Intent(baseContext, PokeInfoActivity::class.java)
        intent.putExtra("id", pokeRandom) // passa o id para a initUi()
        startActivity(intent)
        finish()
    }

    //Infla o SearchBAr na ActionBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    //faz a chamada do searchView no layout
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search -> {
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }



    //envia as informações do item clicado para a tela de informações
    private fun initUI() {

        pokeListRecyclerView.layoutManager = LinearLayoutManager(this)
        pokeListRecyclerView.adapter = PokeListAdapter {
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        inclui os itens do adapter na RecyclerView
        viewModel.getPokemonList()
        viewModel.pokemonList.observe(this, androidx.lifecycle.Observer { list ->
            (pokeListRecyclerView.adapter as PokeListAdapter).setData(list)
        })
    }
    }
    
    
    
 ## PokeInfoActivity
    
    class PokeInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeInfoViewModel// Instancia a classe PokeInfoViewModel
    private var mediaPlayer: MediaPlayer? = null //Cria um objeto do tipo MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokeinfo)


        //cria um botão de menu "voltar" na actionBAr
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "PokeInfo"
        
        //cria um id de pokemon randômico para ser apresnetado
        val fabRandom = findViewById<FloatingActionButton>(R.id.fabRandom)
        fabRandom.setOnClickListener {
            randomPokemon()
        }
        
        //recupera a lista de pokemons acessadas pelo retrofit
        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)
        initUI()
        
        //método que chama a função para incluir coloca os itens do adapter na recyclerView
        initUI()

    }

    private fun play() {
        mediaPlayer?.start()//inicia automaticamente o som do pokemon, sem controle de midia

    }


    //ao gerar um novo pokemon ou voltar para a tela home, o som é parado automaticamente
    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
    }

    //gera um id de pokemon automaticamente
    private fun randomPokemon() {
        fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) + start

        val pokeRandom = (1..151).random()
        val intent = Intent(this, PokeInfoActivity::class.java)
        intent.putExtra("id", pokeRandom)
        startActivity(intent)
        finish()
    }


    //configura ação do botao voltar da actionbar para a Home
    override fun onBackPressed() {
        startActivity(
            Intent(
                this,
                PokeListActivity::class.java
            )
        )
        finishAffinity()
        return
    }

     //configura ação do botao voltar da actionbar para a Home
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(
                    Intent(
                        this,
                        PokeListActivity::class.java
                    )
                )
                finishAffinity()
            }
        }
        return true
    }

    
    private fun initUI() {
        //recupera o id gerado pela função getPokemonInfo
        val id = intent.extras?.get("id") as Int 
        
        // formata esse é para uma string com 3 casas, sendo as duas sendo o numero '0' caso seja uma casa vazia
        val formattedNumber = id.toString().padStart(3, '0')


        viewModel.getPokemonInfo(id)
        viewModel.pokemonInfo.observe(this) { pokemon ->
        
            //configura o nome do pokemon, já capitalizado
            textViewPokemonNameInfo.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            //configura o peso do pokemon ja convertigo em kilogramas
            "${pokemon.weight / 10.0}Kg".also { textViewPokemonHeightInfo.text = it }
            
            //configura a altura do pokemon, fazendo a conversão de metros para centímetros, caso seja menor que 1metro
            if (pokemon.height / 10.0 < 1.0) {
                "${pokemon.height}0cm".also { textViewPokemonWidthInfo.text = it }
            } else {
                "${pokemon.height / 10.0}m".also { textViewPokemonWidthInfo.text = it }
            }
            
            //configura o numero do pokemon já formatado com as três casas 
            "N° $formattedNumber".also { id_number.text = it }
            
            
            pokeSounds()//chama o método de tocar o som do pokémon com o parâmetro id já settado
            
            //configura a imagem do pokemon, atraves de um link da propaira api, e já com o numero do ID formatado e como string
            Glide.with(this)
                .load("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${formattedNumber}.png")
                .into(imageView)
        }

    }

    private fun pokeSounds() {
          
        //Quando recebe o parâmetro id formatado toca o som do pokemon referente ao id dele
        when (id_number.text) {
            "N° 001" -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.bulbasaursound)
                play()}
                
               .
               .
               {151}// 151 sons de pokemons configurados

        }
    }

    }
   
 ## PokeListViewModel
 
    class PokeListViewModel : ViewModel() {
    
    //Configuração do retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/") // URL base para a chamada da API
        .addConverterFactory(GsonConverterFactory.create()) //Cria um Json formatado para o uso simplificado
        .build()

    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    val pokemonList = MutableLiveData<List<PokeResult>>() /Cria a lista de pokemons

    fun getPokemonList() {
        val call = service.getPokemonList(151, 0) //faz o request para os 151 pokemons

        call.enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(
                call: Call<PokeApiResponse>,
                response: Response<PokeApiResponse>
            ) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)//carrega a lista no adapter
                }

            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                call.cancel()
            }
            
            
  ## PokeListAdapter
  
     class PokeListAdapter(val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<PokeListAdapter.SearchViewHolder>() {
    var pokemonList: List<PokeResult> = emptyList<PokeResult>()
    
    
    //cria a lista a ser preenchida 
    fun setData(list: List<PokeResult>){
        pokemonList = list
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
    
         //infla o Layout dos cards dos pokemons
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon_search, parent,false))
    }
    
    //retorna a quantidade de poekemons
    override fun getItemCount(): Int {
        return pokemonList.size
    }


    //realiza o Bind  para o viw holder
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position] //pocição do pokemon no click
        val formattedNumber = (position + 1).toString().padStart(3, '0')// soma + 1 ao click do pokemon para ajustar o numero do id ja formatado com 3 casas
        val imagePokemon = holder.itemView.imagePokemon 
        ("Nº $formattedNumber").also { holder.itemView.textViewPokemonNumber.text = it }// seta o texto de ID do pokemon ja formatado
        holder.itemView.setOnClickListener { pokemonClick(position + 1) }// recupera a posição na lista
        
        //formata o nome do pokemon ja capitalizado
        holder.itemView.textViewPokemonName.text = pokemon.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
        
        //configura a imagem do pokemon atravéz do Glide já com id formatado
        Glide.with(imagePokemon)
            .load("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${formattedNumber}.png")
            .into(imagePokemon)
    }
    
    
    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
    }
        })
    }

    }
    
 ## PokeInfoViewModel
 
     
    class PokeInfoViewModel : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    val pokemonInfo = MutableLiveData<Pokemon>()// Recupera as informações dos pokemons
    
    
    fun getPokemonInfo(id: Int) {
        val call = service.getPokemonInfo(id)
        
        //recupera as informações do corpo do Json da api(demias informações dos pokemons)
        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)

                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }
            
            
            
  ## PokeApiService
  
    interface PokeApiService {
    
    //chama a lista de pokemons
    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokeApiResponse>
    
    //chama as informações dos pokemons(Body)
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>

    }

        })

    }

    }
    
    
 ## Pokemon Data Class
    
    //Serializa as informações que serão recuperadas
    data class Pokemon(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("weight") val weight: Int,
    @Expose @SerializedName("height") val height: Int
    )
    
    
 ## PokemonApiResponse
 
 
    //Serializa a paginação dos pokemons
    data class PokeApiResponse(
    @Expose @SerializedName("count") val count: Int,
    @Expose @SerializedName("next") val next: String,
    @Expose @SerializedName("previous") val previous: String,
    @Expose @SerializedName("results") val results: List<PokeResult>
    )
    
     //Serializa os resultados do pokemons através do nome e url do mesmo
    data class PokeResult(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String,
    )
    
    
