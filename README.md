Encryption example for asymmetric keys generated with GCP KMS.

**Requires Java 8+**

1. Create an asymmetric key with GCP's [KMS](https://cloud.google.com/kms/docs/creating-asymmetric-keys#kms-create-key-ring-web)
2. Download the public key using the [Web-UI](https://cloud.google.com/kms/docs/retrieve-public-key#kms-get-public-key-web)
3. Save it as **key.pub** in the same folder as the **Main.java** file.
4. Run ```javac Main.java && java Main "key.pub" "text"```

Sample output:
```
Aug 28, 2020 5:49:39 PM Main main
INFO: sfNT3IMcGFUDQBnMbRn2I6HsjyUptOooJGyHubtm65WcQA18lW16Br...
```

5. Follow [the instructions](https://cloud.google.com/kms/docs/encrypt-decrypt-rsa#kms-decrypt-asymmetric-cli) to decrypt the text.