package lopez.ermesto.thecheesery.data

import lopez.ermesto.thecheesery.domain.Producto
import lopez.ermesto.thecheesery.R

val coldDrinks =  listOf<Producto>(
    Producto("Caramel Frap", 5.0f, R.drawable.caramel_frap, "Caramel syrup meets coffee, milk and ice and whipped cream and buttery caramel sauce layer the love on top." ),
    Producto("Chocolate Frap", 6f,R.drawable.chocolate_frap, "Rich mocha-flavored sauce meets up with chocolaty chips, milk and ice for a blender bash."),
    Producto("Cold Brew", 3f, R.drawable.coldbrew, "Created by steeping medium-to-coarse ground coffee in room temperature water for 12 hours or longer."),
    Producto("Matcha Latte", 4f,R.drawable.matcha, "Leafy taste of matcha green tea powder with creamy milk and a little sugar for a flavor balance that will leave you feeling ready and raring to go."),
    Producto("Oreo Milkshake", 7f, R.drawable.oreomilkshake, "Chocolate ice cream, and oreo cookies. Topped with whipped cream with cocoa and chocolate syrup.", ),
    Producto("Peanut Milkshake", 7f,R.drawable.peanutmilkshake, "Vanilla ice cream, mixed with peanut butter and chocolate.")
)

val hotDrinks = listOf(
    Producto("Latte", 6F, R.drawable.latte, "Coffee drink made with espresso and steamed milk"),
    Producto("Hot chocolate", 5F, R.drawable.hotchocolate, "Heated drink consisting of shaved chocolate, topped with marshmallows."),
    Producto("Espresso", 4F, R.drawable.espresso, "Full-flavored, concentrated form of coffee."),
    Producto("Chai Latte", 6F, R.drawable.chailatte, "Spiced tea concentrate with milk"),
    Producto("Capuccino", 7F, R.drawable.capuccino, "A cappuccino is an espresso-based coffee drink, prepared with steamed foam."),
    Producto("American coffee", 2F, R.drawable.americano, "Espresso with hot water")
)

val sweets = listOf(
    Producto("Blueberry cake", 6F, R.drawable.blueberrycake, "Vanilla cake flavor, topped with cheese topping and blueberries."),
    Producto("Chocolate cupcake", 3F, R.drawable.chocolatecupcake, "Chocolate cupcakes topped with butter cream and cherries"),
    Producto("Lemon tartalette", 4F, R.drawable.lemontartalette, "Pastry shell with a lemon flavored filling"),
    Producto("Red Velvet cake", 6F, R.drawable.redvelvetcake, "Soft, moist, buttery cake topped with an easy cream cheese frosting."),
    Producto("Cherry cheesecake", 7F, R.drawable.strawberrycheesecake, "This cherry topped cheesecake is positively creamy and delicious and will be your new favorite dessert."),
    Producto("Tiramisu", 6F, R.drawable.tiramisu, "Coffee-flavored Italian dessert")
)

val salties = listOf(
    Producto("Chicken crepes", 6F, R.drawable.chickencrepes, "Fine crepes stuffed with Alfredo chicken, spinach and mushrooms."),
    Producto("Club Sandwich", 5F, R.drawable.clubsandwich, "A delicious sandwich served with french fries."),
    Producto("Panini", 4F, R.drawable.ic_panini, "Sandwich made with Italian bread served warmed by grilling."),
    Producto("Philly cheese steak", 6F, R.drawable.phillycheesesteak, "Smothered in grilled onions, green peppers, mushrooms, and Provolone."),
    Producto("Nachos", 7F, R.drawable.nachos, "Tortilla chips layered with beef and melted cheddar cheese. Served with fried beans, guacamole, pico de gallo, and sour topping.")
)