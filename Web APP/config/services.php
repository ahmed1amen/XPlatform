<?php

return [

    /*
    |--------------------------------------------------------------------------
    | Third Party Services
    |--------------------------------------------------------------------------
    |
    | This file is for storing the credentials for third party services such
    | as Mailgun, Postmark, AWS and more. This file provides the de facto
    | location for this type of information, allowing packages to have
    | a conventional file to locate the various service credentials.
    |
    */
    'firebase' => [
        "type" => "service_account",
        "project_id" => "firebase_project_id",
        "private_key_id" => "firebase_private_key_id",
        "private_key" => "firebase_private_key",
        "client_id" => "firebase_client_id",
        "auth_uri" => "firebase_auth_uri",
        "token_uri" => "firebase_token_uri",
        "auth_provider_x509_cert_url" => "firebase_auth_provider_x509_cert_url",
        "client_x509_cert_url" => "firebase_client_x509_cert_url",
        "database_url" => "firebase_database_url"
    ],

    'mailgun' => [
        'domain' => env('MAILGUN_DOMAIN'),
        'secret' => env('MAILGUN_SECRET'),
        'endpoint' => env('MAILGUN_ENDPOINT', 'api.mailgun.net'),
    ],

    'postmark' => [
        'token' => env('POSTMARK_TOKEN'),
    ],

    'ses' => [
        'key' => env('AWS_ACCESS_KEY_ID'),
        'secret' => env('AWS_SECRET_ACCESS_KEY'),
        'region' => env('AWS_DEFAULT_REGION', 'us-east-1'),
    ],

];
