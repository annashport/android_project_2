package com.example.myyyyapplication.data.source.remote

import com.example.myyyyapplication.data.source.remote.model.WorkshopModel

class WorkshopMockDataSourceImpl: WorkshopRemoteDataSource {

    override suspend fun loadWorkshops(): List<WorkshopModel> {
        return listOf(
            WorkshopModel(
                name = "Майстерня Novikova Paint&Craft",
                address = "м. Вінниця, вул. Хлібна 14",
                phone = "(073) 419 32 22",
                website = "https://www.paintcraftworkroom.com/",
                interests = "Малювання",
                days = "Сб-Нд",
                hours = "9:00-17:00",
                price = "Більше 100 грн/год",
                longitude = 28.46022603367229,
                latitude = 49.23368828782602

            ),
            WorkshopModel(
                name = "Центр розвитку дитини «Бджілка»",
                address = "м. Вінниця, вул. Ватутіна, 27",
                phone = "(063) 286 82 82, (068) 644 00 44",
                website = "http://www.bdjilka.vn.ua/",
                interests = "Рукоділля",
                days = "Пн-Нд",
                hours = "9:00-18:00",
                price = "До 100 грн/год",
                longitude = 28.534603598090545,
                latitude = 49.24547054738144
            ),
            WorkshopModel(
                name = "Дитячий центр “BeFirst.Kids”",
                address = "м. Вінниця, вул. Марії Литвиненко-Вольгемут, 26",
                phone = "(067) 702-6045, (093) 093-9141",
                website = "www.befirst.in.ua",
                interests = "Театральні гуртки",
                days = "Пн-Нд",
                hours = "9:00-18:00",
                price = "До 100 грн/год",
                longitude = 28.45011871158231,
                latitude = 49.226701299307344
            ),
            WorkshopModel(
                name = "Курси акторської майстерності “Червоний Крокодил“",
                address = "м. Вінниця, вул. Миколи Оводова, 22",
                phone = "(067) 184 60 53",
                website = "https://www.facebook.com/schoolredcroco",
                interests = "Театральні гуртки",
                days = "Пн-Пт",
                hours = "9:00-19:00",
                price = "Більше 100 грн/год",
                longitude = 28.46878186925433,
                latitude = 49.23406534814523
            ),
            WorkshopModel(
                name = "Вінницька дитяча музична школа №1",
                address = "м. Вінниця, вул. Архітектора Артинова, 21",
                phone = "(0432) 562-830",
                website = "https://msh1.edu.vn.ua/",
                interests = "Музика, вокал",
                days = "Пн-Пт",
                hours = "9:00-17:00",
                price = "До 100 грн/год",
                longitude = 28.466537698090193,
                latitude = 49.234427990744194
            ),
            WorkshopModel(
                name = "Вінницька дитяча музична школа №2",
                address = "м. Вінниця, вул. Червоноармійська, 16",
                phone = "(0432) 261-456, (0432) 261-637",
                website = "vdmsh2.vn.ua",
                interests = "Музика, вокал",
                days = "Пн-Пт",
                hours = "9:00-17:00",
                price = "До 100 грн/год",
                longitude = 28.486810098090743,
                latitude = 49.24682822502492
            ),
            WorkshopModel(
                name = "Artinov Studio, приватна музична школа",
                address = "м. Вінниця, вул. Василя Стуса 26",
                phone = "(097) 776 00 68",
                website = "https://studio.artinov.school/",
                interests = "Музика, вокал",
                days = "Пн-Нд",
                hours = "9:00-19:00",
                price = "Більше 100 грн/год",
                longitude = 28.457395598090013,
                latitude = 49.22861702404529
            ),
            WorkshopModel(
                name = "Вінницька дитяча школа мистецтв",
                address = "м. Вінниця, вул. Островського, 85",
                phone = "(0432) 275-095",
                website = "schoolarts.vn.ua",
                interests = "Музика, вокал",
                days = "Пн-Пт",
                hours = "9:00-17:00",
                price = "До 100 грн/год",
                longitude = 28.491826226926126,
                latitude = 49.23295157177888
            ),
            WorkshopModel(
                name = "Школа програмування для дітей “ROBOCODE”",
                address = "м. Вінниця, вулиця Академіка Ющенко, 6",
                phone = "(093) 170-6445",
                website = "robocode.ua",
                interests = "Технічні гуртки",
                days = "Пн-Нд",
                hours = "9:00-19:00",
                price = "Більше 100 грн/год",
                longitude = 28.443050682746133,
                latitude = 49.2204667925482
            ),
            WorkshopModel(
                name = "Школа “Гарант”",
                address = "м. Вінниця, вул. Київська, 134",
                phone = "(067) 440-5546, (093) 884-7201",
                website = "https://garant-school.com/",
                interests = "Технічні гуртки",
                days = "Пн-Нд",
                hours = "9:00-19:00",
                price = "Більше 100 грн/год",
                longitude = 28.47401231343488,
                latitude = 49.26363910643602
            ),
            WorkshopModel(
                name = "Комп’ютерна академія “ITSTEP”",
                address = "м. Вінниця, пл. Гагаріна, 2",
                phone = "(067) 522-1255, (073) 797-8831",
                website = "https://garant-school.com/",
                interests = "Технічні гуртки",
                days = "Пн-Нд",
                hours = "9:00-19:00",
                price = "Більше 100 грн/год",
                longitude = 28.455978769254354,
                latitude = 49.233235176881955
            ),
            WorkshopModel(
                name = "Бізнес-школа для дітей “Rainbow”",
                address = "м. Вінниця, вул. Пирогова 52а",
                phone = "(067) 631-4351",
                website = "https://rainbow-vinnytsia.webnode.com.ua/",
                interests = "Саморозвиток",
                days = "Сб-Нд",
                hours = "9:00-18:00",
                price = "До 100 грн/год",
                longitude = 28.449325384597692,
                latitude = 49.228176653770475
            ),
            WorkshopModel(
                name = "Танцювальна студія “Альфа-Денс”",
                address = "м. Вінниця, вул. 600-річчя, 66-Б",
                phone = "(096) 035-16-68",
                website = "https://alphadance.com.ua/",
                interests = "Спортивні секції",
                days = "Пн-Нд",
                hours = "9:00-19:00",
                price = "Більше 100 грн/год",
                longitude = 28.423809453910312,
                latitude = 49.22257249532725
            ),
            WorkshopModel(
                name = "Спорткомплекс “Аква-Він“",
                address = "вул. Академіка Янгеля, 48",
                phone = "(097) 936-8427",
                website = "https://vezha.ua/abonement-za-yakym-vinnychany-zmozhut-hodyty-v-akva-vin-opratsyuyut-na-lyutyj/",
                interests = "Спортивні секції",
                days = "Пн-Нд",
                hours = "9:00-19:00",
                price = "Більше 100 грн/год",
                longitude = 28.485370369254774,
                latitude = 49.24452615795192
            ),
            WorkshopModel(
                name = "SOUL DANCE CENTRE",
                address = "м. Вінниця, вул. Соборна, 8",
                phone = "(063) 695-0664",
                website = "http://souldancecentre.com/",
                interests = "Спортивні секції",
                days = "Пн-Нд",
                hours = "9:00-19:00",
                price = "Більше 100 грн/год",
                longitude = 28.474554011582597,
                latitude = 49.23366670997444
            ),
            WorkshopModel(
                name = "СДЮСШ з легкої атлетики",
                address = "м. Вінниця, вул. Генерала Арабея, 3",
                phone = "(043) 235-4245",
                website = "https://ua.prostobaby.com.ua/spravochniki/razvitie_detey/id/183571",
                interests = "Спортивні секції",
                days = "Пн-Пт",
                hours = "9:00-18:00",
                price = "Безкоштовні",
                longitude = 28.439248153911045,
                latitude = 49.239684075835456
            ),
            WorkshopModel(
                name = "ВІННИЦЬКА ОБЛАСНА ФЕДЕРАЦІЯ ВЕСЛУВАННЯ НА БАЙДАРКАХ І КАНОЕ",
                address = "м. Вінниця вул. Князів Коріатовичів, 123",
                phone = "(097) 870-0600",
                website = "https://canoe-sprint.vn.ua/shkoli-vesluvannja-u-vinnickij-oblasti",
                interests = "Спортивні секції",
                days = "Пн-Нд",
                hours = "9:00-18:00",
                price = "Безкоштовні",
                longitude = 28.4629392557617,
                latitude = 49.22581524238848
            ),
            WorkshopModel(
                name = "СДЮСШ з баскетболу",
                address = "м. Вінниця, вул. Пирогова, 4",
                phone = "(067) 431-6546",
                website = "https://edusearch.com.ua/vn/school/vinnicka-miska-specializovana-dityacho-yunacka-shk",
                interests = "Спортивні секції",
                days = "Пн-Пт",
                hours = "9:00-19:00",
                price = "Безкоштовні",
                longitude = 28.45615296740304,
                latitude = 49.23221061472379
            ),
            WorkshopModel(
                name = "СДЮСШ з акробатики",
                address = "м. Вінниця, вул. Червонохрестівська, 11",
                phone = "(043) 269-7968",
                website = "https://ua.prostobaby.com.ua/spravochniki/razvitie_detey/id/183568",
                interests = "Спортивні секції",
                days = "Пн-Пт",
                hours = "9:00-19:00",
                price = "Безкоштовні",
                longitude = 28.47453762692616,
                latitude = 49.234700412148364
            ),
            WorkshopModel(
                name = "Центр Іноземних Мов ASAP",
                address = "м. Вінниця, вул. Ак. Ющенка, 6",
                phone = "(096) 390-0090",
                website = "http://asap.vn.ua/",
                interests = "Вивчення мов",
                days = "Пн-Нд",
                hours = "9:00-18:00",
                price = "Більше 100 грн/год",
                longitude = 28.44298631160597,
                latitude = 49.22050185091505
            ),
            WorkshopModel(
                name = "Школа “WizarD”",
                address = "м. Вінниця, пр. Юності, 61",
                phone = "(097) 077-4303",
                website = "http://wizardschool.com.ua/",
                interests = "Вивчення мов",
                days = "Пн-Нд",
                hours = "9:00-18:00",
                price = "Більше 100 грн/год",
                longitude = 28.411884228776916,
                latitude = 49.22299150157598
            ),
        )
    }
}