$(window).on('load', function () {
    setTimeout(function () {
        $(".loader-page").css({ visibility: "hidden", opacity: "0" })
    }, 2000);

});

var mainApp = angular.module("mainApp", []);
mainApp.controller('ControladorPokemon', function ($scope, $http) {
    $scope.PokemonAux = {};
    $scope.Pokemon = {};
    $scope.Pokemons = [];
    $scope.PokemonsAux = [];
    $scope.nombre = "";
    $scope.types = [];
    $scope.types2 = [];
    $scope.moves = [];
    Pok = {};
    $http.get("http://localhost:8080/APIPOKE/list")

        .then(function (response) {
			
            $scope.aux = response.data;
            $scope.enviarDatos($scope.aux)
        });


    $scope.enviarDatos = function (arreglo) {

		
        if (typeof (arreglo) == "string") {
            $scope.Pokemon = {};
            $scope.Pokemons = [];

        }
        else {
            	$scope.Pokemons = arreglo;
           		
                   
            }
        }

        $scope.verPokemon = function (i) {

            $http.get("http://localhost:8080/APIPOKE/load/" + i)

                .then(function (response) {
					console.log($scope.Pokemons);
                    $scope.Pokemon = response.data;
                    $('#dialogo1').modal('show');
                    
                    $scope.type = $scope.Pokemon.tipo;
                    
                    $scope.nombre = $scope.Pokemon.nombre;
                    $scope.url = $scope.Pokemon.foto;
                    $scope.weight = $scope.Pokemon.peso;
                    $scope.movimientos = $scope.Pokemon.habilidades;
               
                });


        
    }
});