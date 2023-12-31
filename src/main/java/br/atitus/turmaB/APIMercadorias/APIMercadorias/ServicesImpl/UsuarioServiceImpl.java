package br.atitus.turmaB.APIMercadorias.APIMercadorias.ServicesImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.atitus.turmaB.APIMercadorias.APIMercadorias.Entities.Usuario;
import br.atitus.turmaB.APIMercadorias.APIMercadorias.Repositories.GenericRepository;
import br.atitus.turmaB.APIMercadorias.APIMercadorias.Repositories.UsuarioRepository;
import br.atitus.turmaB.APIMercadorias.APIMercadorias.Services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	final UsuarioRepository usuarioRepository;
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public GenericRepository<Usuario> getRepository() {
		return usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o seguinte e-mail: " + email));
		return usuario;
	}

	@Override
	public void validarSave(Usuario objeto) throws Exception {
		UsuarioService.super.validarSave(objeto);
		if (objeto.getPassword().isEmpty())
			throw new Exception("Password não pode ser vazio");
	}
}
