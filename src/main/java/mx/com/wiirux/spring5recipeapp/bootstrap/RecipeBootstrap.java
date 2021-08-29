package mx.com.wiirux.spring5recipeapp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import mx.com.wiirux.spring5recipeapp.domain.Categoria;
import mx.com.wiirux.spring5recipeapp.domain.Dificultad;
import mx.com.wiirux.spring5recipeapp.domain.Ingrediente;
import mx.com.wiirux.spring5recipeapp.domain.Notas;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;
import mx.com.wiirux.spring5recipeapp.repositories.CategoriaRepositorio;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.repositories.UnidadMedidaRepositorio;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	private CategoriaRepositorio cr;
	private RecetaRepositorio rr;
	private UnidadMedidaRepositorio umr;
	
	public RecipeBootstrap(CategoriaRepositorio cr, RecetaRepositorio rr, UnidadMedidaRepositorio umr) {
		this.cr = cr;
		this.rr = rr;
		this.umr = umr;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		rr.saveAll(getRecipes());
	}
	
	public List<Receta> getRecipes(){
		List<Receta> recetas = new ArrayList<>(2);
		
		//obtener unidades de medida
		Optional<UnidadMedida> unidadMedidaCada = umr.findByDescripcion("Cada");
		
		if(!unidadMedidaCada.isPresent()) {
			throw new RuntimeException("No está disponible la unidad de medida Cada");
		}
		
		Optional<UnidadMedida> unidadMedidaCucharada = umr.findByDescripcion("Cucharada");
		
		if(!unidadMedidaCucharada.isPresent()) {
			throw new RuntimeException("No está disponible la unidad de medida Cucharada");
		}
		
		Optional<UnidadMedida> unidadMedidaCucharadaPequenia = umr.findByDescripcion("Cucharada pequenia");
		
		if(!unidadMedidaCucharadaPequenia.isPresent()) {
			throw new RuntimeException("No está disponible la unidad de medida Cucharada pequenia");
		}
		
		Optional<UnidadMedida> unidadMedidaPizca = umr.findByDescripcion("Pizca");
		
		if(!unidadMedidaPizca.isPresent()) {
			throw new RuntimeException("No está disponible la unidad de medida Pizca");
		}
		
		Optional<UnidadMedida> unidadMedidaMedioLitro = umr.findByDescripcion("Medio litro");
		
		if(!unidadMedidaMedioLitro.isPresent()) {
			throw new RuntimeException("No está disponible la unidad de medida Medio litro");
		}
		
		Optional<UnidadMedida> unidadMedidaTaza = umr.findByDescripcion("Taza");
		
		if(!unidadMedidaTaza.isPresent()) {
			throw new RuntimeException("No está disponible la unidad de medida Taza");
		}
		
		//obtener optionals
		UnidadMedida cadaUM = unidadMedidaCada.get();
		UnidadMedida cucharadaUM = unidadMedidaCucharada.get();
		UnidadMedida cucharadaPequeniaUM = unidadMedidaCucharadaPequenia.get();
		UnidadMedida pizcaUM = unidadMedidaPizca.get();
		UnidadMedida medioLitroUM = unidadMedidaMedioLitro.get();
		UnidadMedida tazaUM = unidadMedidaTaza.get();
		
		//obtener categorias
		Optional<Categoria> americanaCategoriaOptional = cr.findByDescripcion("Americana");
		if(!americanaCategoriaOptional.isPresent()) {
			throw new RuntimeException("Categoria no encontrada Americana");
		}
		
		Optional<Categoria> mexicanaCategoriaOptional = cr.findByDescripcion("Mexicana");
		if(!mexicanaCategoriaOptional.isPresent()) {
			throw new RuntimeException("Categoria no encontrada Mexicana");
		}
		
		Categoria americanaCategoria = americanaCategoriaOptional.get();
		Categoria mexicanaCategoria = mexicanaCategoriaOptional.get();
		
		//Yummy Guac
        Receta recetaGuacamole = new Receta();
        recetaGuacamole.setDescripcion("Perfect Guacamole");
        recetaGuacamole.setTiempoPreparacion(10);
        recetaGuacamole.setTiempoCooccion(0);
        recetaGuacamole.setDificultad(Dificultad.FACIL);
        recetaGuacamole.setDirecciones("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Notas notasGuacamole = new Notas();
        notasGuacamole.setNotasRecetas("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        notasGuacamole.setReceta(recetaGuacamole);
        recetaGuacamole.setNotas(notasGuacamole);
        
        recetaGuacamole.getIngredientes().add(new Ingrediente("ripe avocados", new BigDecimal(2), cadaUM, recetaGuacamole));
        recetaGuacamole.getIngredientes().add(new Ingrediente("Kosher salt", new BigDecimal(".5"), cucharadaPequeniaUM, recetaGuacamole));
        recetaGuacamole.getIngredientes().add(new Ingrediente("fresh lime juice or lemon juice", new BigDecimal(2), cucharadaUM, recetaGuacamole));
        recetaGuacamole.getIngredientes().add(new Ingrediente("minced red onion or thinly sliced green onion", new BigDecimal(2), cucharadaUM, recetaGuacamole));
        recetaGuacamole.getIngredientes().add(new Ingrediente("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), cadaUM, recetaGuacamole));
        recetaGuacamole.getIngredientes().add(new Ingrediente("Cilantro", new BigDecimal(2), cucharadaUM, recetaGuacamole));
        recetaGuacamole.getIngredientes().add(new Ingrediente("freshly grated black pepper", new BigDecimal(2), pizcaUM, recetaGuacamole));
        recetaGuacamole.getIngredientes().add(new Ingrediente("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), cadaUM, recetaGuacamole));

        recetaGuacamole.getCategorias().add(americanaCategoria);
        recetaGuacamole.getCategorias().add(mexicanaCategoria);
        
        //add to return list
        recetas.add(recetaGuacamole);
        
        //Yummy Tacos
        Receta recetaTacos = new Receta();
        recetaTacos.setDescripcion("Spicy Grilled Chicken Taco");
        recetaTacos.setTiempoCooccion(9);
        recetaTacos.setTiempoPreparacion(20);
        recetaTacos.setDificultad(Dificultad.MODERADO);

        recetaTacos.setDirecciones("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notas notasTacos = new Notas();
        notasTacos.setNotasRecetas("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        notasTacos.setReceta(recetaTacos);
        recetaTacos.setNotas(notasTacos);


        recetaTacos.getIngredientes().add(new Ingrediente("Ancho Chili Powder", new BigDecimal(2), cucharadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("Dried Oregano", new BigDecimal(1), cucharadaPequeniaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("Dried Cumin", new BigDecimal(1), cucharadaPequeniaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("Sugar", new BigDecimal(1), cucharadaPequeniaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("Salt", new BigDecimal(".5"), cucharadaPequeniaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("Clove of Garlic, Choppedr", new BigDecimal(1), cadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("finely grated orange zestr", new BigDecimal(1), cucharadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("fresh-squeezed orange juice", new BigDecimal(3), cucharadaPequeniaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("Olive Oil", new BigDecimal(2), cucharadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("boneless chicken thighs", new BigDecimal(4), cucharadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("small corn tortillasr", new BigDecimal(8), cadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("packed baby arugula", new BigDecimal(3), tazaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("medium ripe avocados, slic", new BigDecimal(2), cadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("radishes, thinly sliced", new BigDecimal(4), cadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("cherry tomatoes, halved", new BigDecimal(".5"), medioLitroUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("red onion, thinly sliced", new BigDecimal(".25"), cadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("Roughly chopped cilantro", new BigDecimal(4), cadaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), tazaUM, recetaTacos));
        recetaTacos.getIngredientes().add(new Ingrediente("lime, cut into wedges", new BigDecimal(4), cadaUM, recetaTacos));

        recetaTacos.getCategorias().add(americanaCategoria);
        recetaTacos.getCategorias().add(mexicanaCategoria);

        recetas.add(recetaTacos);		
		
		return recetas;
	}
}
