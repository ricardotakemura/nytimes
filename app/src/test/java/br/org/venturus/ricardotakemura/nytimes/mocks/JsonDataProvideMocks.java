package br.org.venturus.ricardotakemura.nytimes.mocks;

public final class JsonDataProvideMocks {
    public final String JSON_MOST_POPULAR_OK = "{\"status\": \"OK\"," +
            "\"copyright\": \"Copyright (c) 2018 The New York Times Company.  All Rights Reserved.\"," +
            "\"num_results\": 1," +
            "\"results\": [" +
            "{\"url\": \"https://www.nytimes.com/2018/03/27/opinion/nikolas-cruz-shooting-florida.html\"," +
            "\"adx_keywords\": \"Cruz, Nikolas;Parkland, Fla, Shooting (2018);Marjory Stoneman Douglas High School (Parkland, Fla);School Shootings and Armed Attacks;Mental Health and Disorders\"," +
            "\"column\": \"Op-Ed Contributor\"," +
            "\"section\": \"Opinion\"," +
            "\"byline\": \"By ISABELLE ROBINSON\"," +
            "\"type\": \"Article\"," +
            "\"title\": \"I Tried to Befriend Nikolas Cruz. He Still Killed My Friends.\"," +
            "\"abstract\": \"The notion that the Parkland shootings wouldn’t have occurred if students had been kinder is deeply dangerous.\"," +
            "\"published_date\": \"2018-03-27\"," +
            "\"source\": \"The New York Times\"," +
            "\"id\": 100000005820482," +
            "\"asset_id\": 100000005820482," +
            "\"views\": 1," +
            "\"des_facet\": [\"PARKLAND, FLA, SHOOTING (2018)\"]," +
            "\"org_facet\": [\"MARJORY STONEMAN DOUGLAS HIGH SCHOOL (PARKLAND, FLA)\"]," +
            "\"per_facet\": [\"CRUZ, NIKOLAS\"]," +
            "\"geo_facet\": \"\"," +
            "\"media\": [{ \"type\": \"image\"," +
            "\"subtype\": \"photo\"," +
            "\"caption\": \"Nikolas Cruz on his Instagram account.\"," +
            "\"copyright\": \"Instagram\"," +
            "\"approved_for_syndication\": 0," +
            "\"media-metadata\": [" +
            "{ \"url\": \"https://static01.nyt.com/images/2018/03/27/opinion/27Robinson/merlin_133877490_ffcb086d-bc7f-46a9-a088-d15c4a3c1ff8-square320.jpg\"," +
            "\"format\": \"square320\"," +
            "\"height\": 320," +
            "\"width\": 320}]}]}]}";

    public final String JSON_INVALID_CREDENTIAL = "{\"message\":\"Invalid authentication credentials\"}";

    public final String JSON_SEARCH_OK = "{\"status\": \"OK\","+
            "\"copyright\": \"Copyright (c) 2018 The New York Times Company. All Rights Reserved.\","+
            "\"response\": {\"docs\": ["+
            "{\"snippet\": \"Brazil has netted the equivalent of more than $2 billion in an auction for the exploration of oil and gas fields off the country's coast.\","+
            "\"multimedia\": [" +
            "{\"rank\": 0,"+
            "\"subtype\": \"xlarge\","+
            "\"caption\": null,"+
            "\"credit\": null,"+
            "\"type\": \"image\","+
            "\"url\": \"images/2018/03/31/us/31census/merlin_136086270_649ff75b-c065-4992-b084-9709831b40fc-articleLarge.jpg\","+
            "\"height\": 481,"+
            "\"width\": 600,"+
            "\"legacy\": {"+
            "\"xlarge\": \"images/2018/03/31/us/31census/merlin_136086270_649ff75b-c065-4992-b084-9709831b40fc-articleLarge.jpg\","+
            "\"xlargewidth\": 600,"+
            "\"xlargeheight\": 481"+
            "},"+
            "\"subType\": \"xlarge\","+
            "\"crop_name\": \"articleLarge\"}],"+
            "\"headline\": {"+
            "\"main\": \"Offshore Oil Block Auction Nets Brazil More Than $2 Billion\","+
            "\"kicker\": null,"+
            "\"content_kicker\": null,"+
            "\"print_headline\": \"Offshore Oil Block Auction Nets Brazil More Than $2 Billion\","+
            "\"name\": null,"+
            "\"seo\": null,"+
            "\"sub\": null},"+
            "\"pub_date\": \"2018-03-29T20:09:06+0000\","+
            "\"document_type\": \"article\","+
            "\"score\": 0.31355655}],"+
            "\"meta\": {"+
            "\"hits\": 118402,"+
            "\"offset\": 1,"+
            "\"time\": 102}}}";

    public JsonDataProvideMocks() {
        super();
    }
}
