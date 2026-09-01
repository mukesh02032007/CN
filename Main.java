package main;


import java.util.Scanner;

import server.RootDNSServer;
import server.TLDServer;
import server.AuthoritativeServer;

import resolver.RecursiveResolver;
import resolver.IterativeResolver;

import util.DNSCache;


public class Main {

    public static void main(
            String[] args
    ) {

        // START ROOT SERVER

        Thread rootServer =
                new Thread(
                        new RootDNSServer()
                );


        // START TLD SERVER

        Thread tldServer =
                new Thread(
                        new TLDServer()
                );


        // START AUTHORITATIVE SERVER

        Thread authoritativeServer =
                new Thread(
                        new AuthoritativeServer()
                );


        // DAEMON THREADS

        rootServer.setDaemon(true);

        tldServer.setDaemon(true);

        authoritativeServer.setDaemon(true);


        // START SERVERS

        rootServer.start();

        tldServer.start();

        authoritativeServer.start();


        try {

            Thread.sleep(500);

        } catch (
                InterruptedException e
        ) {

            e.printStackTrace();
        }


        Scanner scanner =
                new Scanner(System.in);


        System.out.println(
                "\n=========================================="
        );

        System.out.println(
                " DNS QUERY ANALYZER AND RESOLVER SIMULATOR"
        );

        System.out.println(
                "=========================================="
        );


        boolean running =
                true;


        while (running) {


            System.out.println(
                    "\n1. Recursive DNS Resolution"
            );

            System.out.println(
                    "2. Iterative DNS Resolution"
            );

            System.out.println(
                    "3. View DNS Cache"
            );

            System.out.println(
                    "4. Clear DNS Cache"
            );

            System.out.println(
                    "5. Exit"
            );


            System.out.print(
                    "\nEnter your choice: "
            );


            int choice;


            try {

                choice =
                        scanner.nextInt();

                scanner.nextLine();

            } catch (Exception e) {

                System.out.println(
                        "Invalid Input!"
                );

                scanner.nextLine();

                continue;
            }


            switch (choice) {


                case 1:

                    System.out.print(
                            "\nEnter Domain Name: "
                    );


                    String recursiveDomain =
                            scanner.nextLine()
                                    .toLowerCase()
                                    .trim();


                    RecursiveResolver.resolve(
                            recursiveDomain
                    );


                    break;


                case 2:

                    System.out.print(
                            "\nEnter Domain Name: "
                    );


                    String iterativeDomain =
                            scanner.nextLine()
                                    .toLowerCase()
                                    .trim();


                    IterativeResolver.resolve(
                            iterativeDomain
                    );


                    break;


                case 3:

                    DNSCache.display();


                    break;


                case 4:

                    DNSCache.clear();


                    break;


                case 5:

                    System.out.println(
                            "\nThank You!"
                    );

                    running =
                            false;


                    break;


                default:

                    System.out.println(
                            "\nInvalid Choice!"
                    );
            }
        }


        scanner.close();
    }
}