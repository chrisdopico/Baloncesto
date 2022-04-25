function VoteZero() {
  $.ajax({
    url: "/Baloncesto/VoteZero",
    type: "POST",
    data: {},
    async: false,
    error: function (jqXHR, textStatus, errorThrown) {
      alert("No se ha podido realizar la operación.");
    },
    success: function (response) {
      alert(response);
    },
  });
}

function verListado() {
  window.location.href = "ListaVotos.jsp";
}
