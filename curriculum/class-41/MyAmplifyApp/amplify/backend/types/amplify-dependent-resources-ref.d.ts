export type AmplifyDependentResourcesAttributes = {
    "auth": {
        "myamplifyapp": {
            "IdentityPoolId": "string",
            "IdentityPoolName": "string",
            "UserPoolId": "string",
            "UserPoolArn": "string",
            "UserPoolName": "string",
            "AppClientIDWeb": "string",
            "AppClientID": "string"
        }
    },
    "analytics": {
        "myamplifyapp": {
            "Region": "string",
            "Id": "string",
            "appName": "string"
        }
    },
    "predictions": {
        "translateTextfc0983a5": {
            "region": "string",
            "sourceLang": "string",
            "targetLang": "string"
        },
        "speechGeneratorf4c325a5": {
            "region": "string",
            "language": "string",
            "voice": "string"
        }
    }
}