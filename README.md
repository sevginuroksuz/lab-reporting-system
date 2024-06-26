## ÖZGÜR YAZILIM 
## LABORATUVAR RAPOR YÖNETİM SİSTEMİ UYGULAMASI
<hr>
Proje bir laboratuvar yönetim sistemi uygulamasının genel hatlarıyla simülasyonunu yapar.

### Entity Classlari
#### Report
id -> Long

fileNumber -> String

patientFirstName -> String

patientLastName -> String

patientIdentityNumber -> String

diagnosisTitle -> String

diagnosisDetails -> String

reportDate -> String

reportImage -> byte

#### Laborant

id -> Long

firstName -> String

lastName -> String

<hr>

<br>

### PROJE NASIL ÇALIŞIR

Proje dockerize edilmiştir.<br>
<i>8081</i> ve <i>5432</i> portları kullanım dışı olmalıdır.
<br>
<b>Projenin kök dizininde olunduğundan emin olunmalıdır.</b>
*Projenin kök dizinine gelmek için şu komutu çalıştırın.<br>
```
cd /path/to/ozgur-yazilim
```
*Sonrasında dockerize edilmiş projeyi aşağıdaki komutla ayağa kaldırın. 
(***Docker Desktop açık olmalılır.***)
```
docker-compose up db -d
docker-compose down
docker-compose up
```

<hr>

Bu şekilde proje ayağa kalkacaktır.

### TEST

Yukarıdaki docker komutları çalıştırıldıktan sonra tarayıcıdan<br>
<i>localhost:8081/</i><br>
endpointine istek atıldığı zaman Hi yazısı görülmelidir.

<hr>

### REQUESTS

* Öncelikle Laborant Oluşturulmalıdır.

```
Endpoint: http://localhost:8081/api/laborants
Request Method: Post
Payload:
{
   "firstName": "Fyodor",
   "lastName": "Dostoyevski"
}
```

Sonuç olarak
```
{
    "id": 3,
    "firstName": "Fyodor",
    "lastName": "Dostoyevski"
}
```

*Laborantı id endpointiyle listeleme
```
Endpoint: http://localhost:8081/api/laborants/1
Request Method: Get

```
Sonuç olarak
```
{
    "id": 1,
    "firstName": "Magnus",
    "lastName": "Carlsen",
    "reports": [
        {
            "id": 3,
            "patientFirstName": "Hayao",
            "patientLastName": "Miyazaki",
            "patientIdentityNumber": "3456789",
            "diagnosisTitle": "Pannic attack",
            "diagnosisDetails": "Details of the diagnosis",
            "reportDate": "2024-06-15",
            "reportImage": "base65EncodedImagQ=="
        },
        {
            "id": 2,
            "patientFirstName": "Friedrich",
            "patientLastName": "Nietzsche",
            "patientIdentityNumber": "1234567",
            "diagnosisTitle": "Depression",
            "diagnosisDetails": "Details of the diagnosis",
            "reportDate": "2024-05-13",
            "reportImage": "base65EncodedImagQ=="
        }
    ]
}
```
*Tüm laborantları listeleme

```
Endpoint: http://localhost:8081/api/laborants/all
Request Method: Get

```
Sonuç olarak
```
[
    {
        "id": 1,
        "firstName": "Magnus",
        "lastName": "Carlsen",
        "reports": [
            {
                "id": 3,
                "patientFirstName": "Hayao",
                "patientLastName": "Miyazaki",
                "patientIdentityNumber": "3456789",
                "diagnosisTitle": "Pannic attack",
                "diagnosisDetails": "Details of the diagnosis",
                "reportDate": "2024-06-15",
                "reportImage": "base65EncodedImagQ=="
            },
            {
                "id": 2,
                "patientFirstName": "Friedrich",
                "patientLastName": "Nietzsche",
                "patientIdentityNumber": "1234567",
                "diagnosisTitle": "Depression",
                "diagnosisDetails": "Details of the diagnosis",
                "reportDate": "2024-05-13",
                "reportImage": "base65EncodedImagQ=="
            }
        ]
    },
    {
        "id": 2,
        "firstName": "Ada",
        "lastName": "Lovelace",
        "reports": [
            {
                "id": 4,
                "patientFirstName": "Frédéric",
                "patientLastName": "Chopin",
                "patientIdentityNumber": "2345678",
                "diagnosisTitle": "Anemia",
                "diagnosisDetails": "Details of the diagnosis",
                "reportDate": "2020-03-11",
                "reportImage": "base66EncodedImagQ=="
            },
            {
                "id": 5,
                "patientFirstName": "Stefan",
                "patientLastName": "Zweig",
                "patientIdentityNumber": "2345671",
                "diagnosisTitle": "Diabetes",
                "diagnosisDetails": "Details of the diagnosis",
                "reportDate": "2020-03-11",
                "reportImage": "base67EncodedImagQ=="
            },
            {
                "id": 6,
                "patientFirstName": "Akira",
                "patientLastName": "Yoshizawa",
                "patientIdentityNumber": "2345678",
                "diagnosisTitle": "Anemia",
                "diagnosisDetails": "Details of the diagnosis",
                "reportDate": "2019-03-11",
                "reportImage": "base64EncodedImagQ=="
            }
        ]
    }
]

```

*Laborant güncelleme

```
Endpoint: http://localhost:8081/api/laborants/update/3
Request Method: Put
Payload:
{
  "laborantFirstName": "Marie",
  "laborantLastName": "Curie",
}

```
Sonuç olarak
```
{
    "id": 3,
    "firstName": "Marie",
    "lastName": "Curie",
    "reports": []
}

```
*Laborant silme
```
Endpoint: http://localhost:8081/api/laborants/delete/5
Request Method: Delete

```
Sonuç olarak
```
Laborant with ID 5 has been deleted successfully.
```
*Sonrasında Rapor oluşturulmalıdır.

```
Endpoint: http://localhost:8081/api/reports
Request Method: Post
Payload:
{
    "diagnosisTitle": "Anemia",
    "diagnosisDetails": "Details of the diagnosis",
    "fileNumber": "1234567892",
    "laborant": {
        "id": 2,
        "firstName": "Ada",
        "lastName": "Lovelace"
    },
    "patientFirstName": "Akira",
    "patientLastName": "Yoshizawa",
    "patientIdentityNumber": "2345678",
    "reportDate": "2019-03-11",
    "reportImage": "base64EncodedImage=="
}
```
Sonuç olarak
```

```

*Rapor güncelleme

```
Endpoint: http://localhost:8081/api/reports/update/1
Request Method: Put
Payload:
{
  "patientFirstName": "Peyami",
  "patientLastName": "Safa",
  "patientIdentityNumber": "1234587",
  "diagnosisTitle": "Updated Diagnosis Title",
  "diagnosisDetails": "Updated Diagnosis Details",
  "reportDate": "2023-06-24",
  "reportImage": "base60EncodedImage=="
}

```
Sonuç olarak
```

```
*Tüm raporları listeleme

```
Endpoint: http://localhost:8081/api/reports/all
Request Method: Get

```
Sonuç olarak
```

```
*Raporu id endpointiyle listeleme
```
Endpoint: http://localhost:8081/api/reports/4
Request Method: Get

```
Sonuç olarak
```
{
    "id": 4,
    "patientFirstName": "Frédéric",
    "patientLastName": "Chopin",
    "patientIdentityNumber": "2345678",
    "diagnosisTitle": "Anemia",
    "diagnosisDetails": "Details of the diagnosis",
    "reportDate": "2020-03-11",
    "reportImage": "base66EncodedImagQ=="
}

```
*Raporları hasta adı, soyadı veya tc kimlik numarasına göre listeleme
```
Endpoint: [http://localhost:8081/api/reports/search-by-date?sortField=date&sortOrder=asc](http://localhost:8081/api/reports/search-by-patient?firstName=Hayao&lastName=Miyazaki&identityNumber=3456789)
Request Method: Get

```
Sonuç olarak
```
[
    {
        "id": 3,
        "patientFirstName": "Hayao",
        "patientLastName": "Miyazaki",
        "patientIdentityNumber": "3456789",
        "diagnosisTitle": "Pannic attack",
        "diagnosisDetails": "Details of the diagnosis",
        "reportDate": "2024-06-15",
        "reportImage": "base65EncodedImagQ=="
    }
]

```
*Raporları laborant ad soyadına göre listeleme
```
Endpoint: http://localhost:8081/api/reports/search-by-laborant?laborantFirstName=Magnus&laborantLastName=Carlsen
Request Method: Get

```
Sonuç olarak
```
[
    {
        "id": 3,
        "patientFirstName": "Hayao",
        "patientLastName": "Miyazaki",
        "patientIdentityNumber": "3456789",
        "diagnosisTitle": "Pannic attack",
        "diagnosisDetails": "Details of the diagnosis",
        "reportDate": "2024-06-15",
        "reportImage": "base65EncodedImagQ=="
    }
]
```

*Raporları tarihe göre listeleme
```
Endpoint: http://localhost:8081/api/reports/search-by-date?sortField=date&sortOrder=asc
Request Method: Get

```
Sonuç olarak
```
[
    {
        "id": 2,
        "patientFirstName": "Friedrich",
        "patientLastName": "Nietzsche",
        "patientIdentityNumber": "1234567",
        "diagnosisTitle": "Depression",
        "diagnosisDetails": "Details of the diagnosis",
        "reportDate": "2024-05-13",
        "reportImage": "base65EncodedImagQ=="
    },
    {
        "id": 3,
        "patientFirstName": "Hayao",
        "patientLastName": "Miyazaki",
        "patientIdentityNumber": "3456789",
        "diagnosisTitle": "Pannic attack",
        "diagnosisDetails": "Details of the diagnosis",
        "reportDate": "2024-06-15",
        "reportImage": "base65EncodedImagQ=="
    },
    {
        "id": 6,
        "patientFirstName": "Akira",
        "patientLastName": "Yoshizawa",
        "patientIdentityNumber": "2345678",
        "diagnosisTitle": "Anemia",
        "diagnosisDetails": "Details of the diagnosis",
        "reportDate": "2019-03-11",
        "reportImage": "base64EncodedImagQ=="
    },
    {
        "id": 5,
        "patientFirstName": "Stefan",
        "patientLastName": "Zweig",
        "patientIdentityNumber": "2345671",
        "diagnosisTitle": "Diabetes",
        "diagnosisDetails": "Details of the diagnosis",
        "reportDate": "2020-03-11",
        "reportImage": "base67EncodedImagQ=="
    },
    {
        "id": 4,
        "patientFirstName": "Frédéric",
        "patientLastName": "Chopin",
        "patientIdentityNumber": "2345678",
        "diagnosisTitle": "Anemia",
        "diagnosisDetails": "Details of the diagnosis",
        "reportDate": "2020-03-11",
        "reportImage": "base66EncodedImagQ=="
    }
]
```
*Rapor silme
```
Endpoint: http://localhost:8081/api/reports/delete/6
Request Method: Delete

```
Sonuç olarak
```
Report with ID 6 has been deleted successfully.
```

```
```

Constraintler:
Patient identity number 7 haneli olmak zorunda<br>
Bir rapor yalnızca bir laborant tarafından tanımlanmış olmalı. Bir laborant ise n tane rapor tanımlayabilir.<br>
Rapor ve Laborant oluşturulurken hiçbir değer null olamaz.<br>
Raporlar hasta adı, soyadı veya tc kimlik numarasına göre listelenirken hepsi dolu olmak zorunda değil.<br>
<br><br>





