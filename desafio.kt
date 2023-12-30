enum class Nivel {
  BASICO,
  INTERMEDIARIO,
  AVANCADO
}

data class Usuario(
    val nome: String,
)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: MutableList<ConteudoEducacional>) {
  private val inscritos = mutableListOf<Usuario>()

  private val usuariosInscritos: Int
    get() = inscritos.size

	// Função para verificar se teria elemento repetido
  private fun <T> existeNaLista(objeto: T, lista: List<T>): Boolean {
    return lista.contains(objeto)
  }

	//TODO: Utilizar o parâmetro $usuario para simular uma matrícula 
  fun matricular(usuario: Usuario) {
    if (!existeNaLista(usuario, inscritos)) {
      this.inscritos.add(usuario)
    } else {
      println("Usuário ${usuario.nome} já está matriculado nesta formação.")
    }
  }

  fun adicionarConteudo(conteudo: ConteudoEducacional) {
    if (!existeNaLista(conteudo, conteudos)) {
      conteudos.add(conteudo)
    } else {
      println("Conteúdo ${conteudo.nome} já foi adicionado nesta formação.")
    }
  }

	// Uma alternativa para exibir os dados de forma mais organizada
  override fun toString(): String {
    return "Formacao { \n" +
        "    Nome: " +
        this.nome +
        "\n" +
        "    Quantidade de Usuários: " +
        this.usuariosInscritos +
        " usuário(s) \n" +
        "    Conteudo(s): ${conteudos.joinToString(", ") { it.nome }} \n" +
        "    Usuário(s) Inscrito(s): ${inscritos.joinToString(", ") { it.nome }} \n" +
        "}"
  }
}

fun main() {

  val usuarios: MutableList<Usuario> = mutableListOf()
  val conteudosEducacionais: MutableList<ConteudoEducacional> = mutableListOf()

	//TODO:Simulando alguns usuário usando as classes em questão.
  // Dados gerados pelo site 4Devs
  usuarios.add(Usuario(nome = "Enrico Oliver César Gomes"))
  usuarios.add(Usuario(nome = "Carla Esther"))
  usuarios.add(Usuario(nome = "Cauê Anthony dos Santos"))

  conteudosEducacionais.add(
      ConteudoEducacional(
          nome = "Trabalhando em Equipes Ágeis",
          duracao = 180,
          nivel = Nivel.BASICO
      )
  )

  conteudosEducacionais.add(
      ConteudoEducacional(
          nome = "Tratamento de Exceções em Kotlin",
          duracao = 120,
          nivel = Nivel.INTERMEDIARIO
      )
  )
  conteudosEducacionais.add(
      ConteudoEducacional(
          nome = "Criando uma Api Rest com Kotlin e Persistência de Dados",
          nivel = Nivel.AVANCADO
      )
  )

  val formacao =
      Formacao(
          nome = "Desenvolvimento Backend com Kotlin e Spring Boot",
          conteudos = conteudosEducacionais
      )

			
  formacao.matricular(usuarios[0])
  formacao.matricular(usuarios[1])
  formacao.matricular(usuarios[2])
  println(formacao)
  println()
}
