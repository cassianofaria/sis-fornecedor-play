/**
 * Created by cassiano on 12/01/16.
 */

var application_url = 'http://localhost:9000/';

var app = angular.module("app", [
    'ngRoute',
    'ngResource',
    'oitozero.ngSweetAlert',
    'app.fornecedores',
    'app.segmentos'
]);


app.config(['$routeProvider', function ($routeProvider) {

    $routeProvider.otherwise('/home', {
        templateUrl: 'app/home/index.html'
    });

    $routeProvider.when('/home', {
        templateUrl: 'app/home/index.html'
    });

    // fornecedores
    $routeProvider.when('/fornecedores', {
        templateUrl: 'app/fornecedores/index.html',
        controller: 'FornecedorIndexController'
    });

    $routeProvider.when('/fornecedores/new', {
        templateUrl: 'app/fornecedores/new.html',
        controller: 'FornecedorNewController',
        resolve: {
            segmentos: function($route, SegmentoFactory) {
                return SegmentoFactory.query().$promise;
            }
        }
    });

    $routeProvider.when('/fornecedores/:id/edit', {
        templateUrl: 'app/fornecedores/edit.html',
        controller: 'FornecedorEditController',
        resolve: {
            fornecedor: function ($route, FornecedorFactory, SegmentoFactory) {
                return FornecedorFactory.get({'id': $route.current.params.id}).$promise;
            },
            segmentos: function($route, SegmentoFactory) {
                return SegmentoFactory.query().$promise;
            }
        }
    });

    // segmentos
    $routeProvider.when('/segmentos', {
        templateUrl: 'app/segmentos/index.html',
        controller: 'SegmentoIndexController'
    });

    $routeProvider.when('/segmentos/new', {
        templateUrl: 'app/segmentos/new.html',
        controller: 'SegmentoNewController'
    });

    $routeProvider.when('/segmentos/:id/edit', {
        templateUrl: 'app/segmentos/edit.html',
        controller: 'SegmentoEditController',
        resolve: {
            segmentoId: function ($route) {
                return $route.current.params.id;
            }
        }
    });

}]);
