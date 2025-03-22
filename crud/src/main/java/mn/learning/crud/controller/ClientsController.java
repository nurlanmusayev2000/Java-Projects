package mn.learning.crud.controller;



import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import mn.learning.crud.modules.Client;
import mn.learning.crud.modules.ClientDto;
import mn.learning.crud.repositories.ClientRepository;

@Controller
@RequestMapping("/clients")
public class ClientsController {
	
	
	@Autowired
	public ClientRepository clientRepo;

	@GetMapping({"","/"})
	public String getClients(Model model) {
		var clients = clientRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		
		model.addAttribute("clients",clients );
		return "clients/index";
	}
	
	@GetMapping("/create")
	public String createClient(Model model) {
		ClientDto clientDto = new ClientDto();
		model.addAttribute("clientDto",clientDto );
		return "clients/create";
	}
	
	@PostMapping("/create")
	public String createClient(@Valid @ModelAttribute ClientDto clientDto,BindingResult result) {
		if (clientRepo.findByEmail(clientDto.getEmail()) != null) {
			result.addError(new FieldError("clientDto", "email", clientDto.getEmail(),false,null,null,"Email adress is already used"));
		}
		if (result.hasErrors()) {
			return "clients/create";
		}
		
		Client client = new Client();
		client.setFirstName(clientDto.getFirstName());
		client.setLastName(clientDto.getLastName());
		client.setEmail(clientDto.getEmail());
		client.setPhone(clientDto.getPhone());
		client.setAddress(clientDto.getAddress());
		client.setStatus(clientDto.getStatus());
		client.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));
		
		clientRepo.save(client);
		
		return "redirect:/clients";
	}
	
	@GetMapping("/edit")
	public String editClient(Model model,@RequestParam int id) {
		Client client = clientRepo.findById(id).orElse(null);
		if(client == null) {
			return "redirect:/clients";
		}
		
		ClientDto clientDto = new ClientDto();
		clientDto.setFirstName(client.getFirstName());
		clientDto.setLastName(client.getLastName());
		clientDto.setEmail(client.getEmail());
		clientDto.setPhone(client.getPhone());
		clientDto.setAddress(client.getAddress());
		clientDto.setStatus(client.getStatus());
		
		model.addAttribute("client",client);
		model.addAttribute("clientDto", clientDto);
		
		return "clients/edit";
		
		
	}
	
	@PostMapping("/edit")
	public String updateClient(@ModelAttribute ClientDto clientDto, @RequestParam int id) {
	    Client client = clientRepo.findById(id).orElse(null);
	    if (client == null) {
	        return "redirect:/clients";
	    }

	    client.setFirstName(clientDto.getFirstName());
	    client.setLastName(clientDto.getLastName());
	    client.setEmail(clientDto.getEmail());
	    client.setPhone(clientDto.getPhone());
	    client.setAddress(clientDto.getAddress());
	    client.setStatus(clientDto.getStatus());

	    clientRepo.save(client);

	    return "redirect:/clients";
	}
	@GetMapping("/delete")
	public String deleteClient(@RequestParam int id) {
		Client client = clientRepo.findById(id).orElse(null);
		if (client  != null) {
			clientRepo.delete(client);
		}
		return "redirect:/clients";
	}
}
