/**
 * Created by cassiano on 12/01/16.
 */

var appSegmentos = angular.module('app.segmentos', []);

appSegmentos.controller("SegmentoIndexController", ['$scope', '$location', 'SegmentoFactory', 'SweetAlert', function ($scope, $location, SegmentoFactory, SweetAlert) {

    $scope.segmentos = [];

    $scope.filtro = {
        'chave': "",
        'valor': ""
    };

    $scope.atualizarLista = function() {
        SegmentoFactory.query(

            $scope.filtro,

            function(response) {
                $scope.segmentos = response;
            },

            function(response) {
                console.log(response);
            }
        );
    };

    $scope.atualizarLista();

    $scope.removeSegmento = function (idSegmento) {

        SweetAlert.swal({
                title: "Excluir?",
                text: "Tem certeza que deseja excluir o segmento?",
                type: "error",
                showCancelButton: true,
                cancelButtonText: "Cancelar",
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Sim",
                closeOnConfirm: true
            },
            function(isConfirmed){
                if(isConfirmed) {
                    SegmentoFactory.delete({'id': idSegmento}).$promise.then(

                        function(response) {
                            $scope.atualizarLista()
                        },

                        function(response) {
                            console.log(response)
                        }
                    );
                }
            });

    };

}]);

appSegmentos.controller("SegmentoNewController", ['$scope', '$location', 'SegmentoFactory', function($scope, $location, SegmentoFactory) {

    $scope.segmento = {};

    $scope.save = function () {
        SegmentoFactory.save($scope.segmento).$promise.then(

            function(response) {
                $scope.segmento = {};
                $location.path('/segmentos');
            },

            function(response) {
                console.log(response.data.message);
                alert(response.data.message);
            }
        );
    };

}]);

appSegmentos.controller("SegmentoEditController", ['$scope', '$location', 'SegmentoFactory', 'segmentoId', function($scope, $location, SegmentoFactory, segmentoId) {

    $scope.segmento = {};


    SegmentoFactory.get({'id': segmentoId}).$promise.then(

        function(response) {
            $scope.segmento = response;
        },

        function(response) {
            console.log(response)
        }
    );

    $scope.update = function () {
        SegmentoFactory.update({'id':$scope.segmento.id}, $scope.segmento).$promise.then(

            function(response) {
                $scope.segmento = {};
                $location.path('/segmentos');
            },

            function(response) {
                console.log(response)
            }
        );
    };

}]);

appSegmentos.factory('SegmentoFactory', ['$http', '$q', '$resource', function($http, $q, $resource) {

    return $resource(application_url + "segmentos/:id",{},  {'update':{method:'PUT' }});

}]);
