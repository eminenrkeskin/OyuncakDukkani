
function listele() {
    $.ajax({
        url: "http://localhost:8080/listele",
        type: "GET",
        success: function (data) {
            var oyuncaklar = data;

            // Genel tablo başlığı
            var genelTablo = "<table style=\"width:100%\"><tr><th>Ad</th><th>Tur</th><th>Desi</th><th>Alış Fiyatı</th><th>Notlar</th></tr>";

            for (var i = 0; i < oyuncaklar.length; i++) {
                // Her oyuncak için tablo verisi
                var tabloVeri = "<tr>" +
                    "<td>" + oyuncaklar[i].ad + "</td>" +
                    "<td>" + oyuncaklar[i].tur + "</td>" +
                    "<td>" + oyuncaklar[i].desi + "</td>" +
                    "<td>" + oyuncaklar[i].alisFiyati + "</td>" +
                    "<td>" + oyuncaklar[i].notlar + "</td>" +
                    "</tr>";

                // Tabloya veriyi ekle
                genelTablo += tabloVeri;
            }

            // Genel tabloyu kapat
            genelTablo += "</table>";

            // Sayfa içerisinde göster
            document.getElementById("oyuncakListesi").innerHTML = genelTablo;
        }
    });
}




// Oyuncak ekle
function oyuncakEkle() {
    // Oyuncak bilgilerini al
    var ad = document.getElementById("ad").value;
    var tur = document.getElementById("tur").value;
    var desi = document.getElementById("desi").value;
    var alisFiyati = document.getElementById("alisFiyati").value;
    var notlar = document.getElementById("notlar").value;

    $.ajax({
        url: "http://localhost:8080/ekle",
        type: "POST",
        contentType: "application/json",  // JSON verisi gönderiyorsanız
        data: JSON.stringify({
            ad: ad,
            tur: tur,
            desi: desi,
            alisFiyati: alisFiyati,
            notlar: notlar
        }),
        success: function (data) {
            alert(data);
            listele();
        }
    });

}


function oyuncakGetir() {
    // Kullanıcının girdiği oyuncak ID'sini al
    var oyuncakID = document.getElementById("oyuncakID").value;

    // Server'dan oyuncak bilgilerini getir
    $.ajax({
        url: "http://localhost:8080/getir?id=" + oyuncakID,
        type: "GET",
        success: function (oyuncak) {
            // Oyuncak detaylarını göster
            var oyuncakDetayHTML = "<h4>Oyuncak Detayları</h4>";
            oyuncakDetayHTML += "<p><strong>ID:</strong> " + oyuncak.id + "</p>";
            oyuncakDetayHTML += "<p><strong>Ad:</strong> " + oyuncak.ad + "</p>";
            oyuncakDetayHTML += "<p><strong>Tür:</strong> " + oyuncak.tur + "</p>";
            oyuncakDetayHTML += "<p><strong>Desi:</strong> " + oyuncak.desi + "</p>";
            oyuncakDetayHTML += "<p><strong>Alış Fiyatı:</strong> " + oyuncak.alisFiyati + "</p>";
            oyuncakDetayHTML += "<p><strong>Notlar:</strong> " + oyuncak.notlar + "</p>";

            // Sayfada göster
            document.getElementById("oyuncakDetay").innerHTML = oyuncakDetayHTML;
        },
        error: function (xhr, status, error) {
            // Hata durumunda kullanıcıya bilgi ver
            document.getElementById("oyuncakDetay").innerHTML = "<p style='color: red;'>Oyuncak getirilirken bir hata oluştu: " + xhr.responseText + "</p>";
        }
    });
}



//sil
function silOyuncak() {
    var oyuncakID = document.getElementById("oyuncakID").value;

    $.ajax({
        url: "http://localhost:8080/sil?id=" + oyuncakID,
        type: "DELETE",
        success: function (data, status, xhr) {
            if (xhr.status === 200) {
                alert("Oyuncak başarıyla silindi.");
                listele(); // Silme başarılıysa tabloyu güncelle
            } else {
                alert("Oyuncak silinirken bir hata oluştu: " + data);
            }
        },
        error: function (xhr, status, error) {
            alert("Oyuncak silinirken bir hata oluştu: " + error);
        }
    });
}






function toggleForm(formId) {
    var form = document.getElementById(formId);

    if (form.style.display === "block") {
        form.style.display = "none";
    } else {
        form.style.display = "block";
    }

    // Form kapatıldığında input alanlarını temizle
    if (form.style.display === "none") {
        clearFormInputs(form);
    }
}

function clearFormInputs(form) {
    var inputs = form.getElementsByTagName("input");
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].value = "";
    }
}


// Oyuncak güncelle
    function oyuncakGuncelle() {
        // Oyuncak bilgilerini al
        var idElement = document.getElementById("guncelleId");
        if (idElement) {
            var id = idElement.value;
            var ad = document.getElementById("guncelleAd").value;
            var tur = document.getElementById("guncelleTur").value;
            var desi = document.getElementById("guncelleDesi").value;
            var alisFiyati = document.getElementById("guncelleAlisFiyati").value;
            var notlar = document.getElementById("guncelleNotlar").value;

            // Oyuncağı güncelle
            $.ajax({
                url: "http://localhost:8080/guncelle/" + id,
                type: "PUT",
                data: JSON.stringify({
                    ad: ad,
                    tur: tur,
                    desi: desi,
                    alisFiyati: alisFiyati,
                    notlar: notlar
                }),
                contentType: "application/json",  // JSON verisi gönderiyorsanız
                success: function (data) {
                    // İşlemin sonucunu göster
                    alert("Oyuncak başarıyla güncellendi.");
                    listele();
                },
                error: function (xhr, status, error) {
                    alert("Oyuncak güncellenirken bir hata oluştu: " + error);
                }
            });
        } else {
            console.error("guncelleId elementi bulunamadı.");
        }
    }
