//TODO make variable take data from database
var departments = [
    {
        "ID":1,
        "settingsID":1,
        "departmentName":"Test",
        "members":1,
        "colIndex":1,
        "efficiency":1,
        "doingLimit":5,
        "doneLimit":1
    },
    {
        "ID":1,
        "settingsID":1,
        "departmentName":"Test",
        "members":1,
        "colIndex":1,
        "efficiency":1,
        "doingLimit":5,
        "doneLimit":1
    },
    {
        "ID":2,
        "settingsID":1,
        "departmentName":"Test",
        "members":1,
        "colIndex":1,
        "efficiency":1,
        "doingLimit":5,
        "doneLimit":1
    },
    {
        "ID":3,
        "settingsID":1,
        "departmentName":"Test",
        "members":1,
        "colIndex":1,
        "efficiency":1,
        "doingLimit":5,
        "doneLimit":1
    },
    {
        "ID":4,
        "settingsID":1,
        "departmentName":"Test",
        "members":1,
        "colIndex":1,
        "efficiency":1,
        "doingLimit":5,
        "doneLimit":1
    },
    {
        "ID":5  ,
        "settingsID":1,
        "departmentName":"Test",
        "members":1,
        "colIndex":1,
        "efficiency":1,
        "doingLimit":5,
        "doneLimit":1
    },
];
var rules = {
    "ID": 1,
    "max_wip_per_person": 2,
    "max_am_people_on_wip": 2,
    "warmup_time": 1.5
};

var Settings_Congi = {
    "ID": 1,
    "boardUUID":"val",
    "constant_setting":[{}],
    "normal_setting":[{}],
    "sized_setting":{
        "ID":1,
        "effortSmall":1.5,
        "probabilitySmall":1,
        "effortMedium":1.5,
        "probabilityMedium":1,
        "effortLarge":1.5,
        "probabilityLarge":1,
        "effortXLarge":1.5,
        "probabilityXLarge":1,
        "desc":"Hello World",
        "dep_sized":
            [
                {
                    "ID":1,
                    //TODO work on this bitch
                },
            ]
    }
    "createStrat" : {
        "ID": 1,
        "constantSettings":{
            "ID": 1,
            "amountHours":1.5,
            "departmentID":1,
            "desc":"VALUE"
        },
        "scrumcreate":{
            "ID":1,
            "iteration_len":5,
            "taskPerITeration":5,
            "wipSub":true,
            "desc":"val"
        },
        "randompush":{
            "ID":1,
            "demandLevel":1.5,
            "batchsize":1,
            "desc":"Hello World"
        } 
    },
};



    /*
    "SettingsConfiguration":[
        {
            "ID":1,
            "boardUUID":"Hello World",
            "constantID":1,
            "normalID":1,
            "sizedID":1,
            "rulesID":1,
            "defaultValue":true,
            "name":"Hello World!"
        }
    ],
    "createStrat":[
        {
            "ID":1,
            "constantpushID":1,
            "randompushID":1,
            "scrumcreateID":1
        }
    ],
    "board":[
        {
            "UUID":"Hello World",
            "creationDate":"2022-05-16"
        }
    ],
    "constantpush":[
        {
            "ID":1,
            "itemsperday":1.5,
            "desc":"Hello World"
        }
    ],
    "normalSettings":[
        {
            "ID":1,
            "meanHours":1.1,
            "variations":1.5,
            "departmentID":1,
            "desc":"Hello World"
        }
    ],
    
    "departmentSized":[
        {
            "ID":1,
            "sizedSettings":1,
            "effort":1,
            "departmentID":1
        }
    ],
    "sizedSettings":[
        {
            "ID":1,
            "effortSmall":1.5,
            "probabilitySmall":1,
            "effortMedium":1.5,
            "probabilityMedium":1,
            "effortLarge":1.5,
            "probabilityLarge":1,
            "effortXLarge":1.5,
            "probabilityXLarge":1,
            "desc":"Hello World"
        }
    ],
    
}*/