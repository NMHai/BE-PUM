0x0040410f:	movl %eax, $0x404000<UINT32>
0x00404114:	pushl $0x0<UINT8>
0x00404116:	pushl $0x4022e3<UINT32>
0x0040411b:	pushl %fs:0
0x00404122:	movl %fs:0, %esp
0x00404129:	pushfw
0x0040412b:	pusha
0x0040412c:	pushl %eax
0x0040412d:	movl %ebx, %eax
0x0040412f:	addl %eax, (%eax)
0x00404131:	pushl $0x438<UINT32>
0x00404136:	pushl $0x0<UINT8>
0x00404138:	call GlobalAlloc@kernel32.dll
GlobalAlloc@kernel32.dll: API Node	
0x0040413b:	movl 0x8(%ebx), %eax
0x0040413e:	movl %eax, %ebx
0x00404140:	addl %eax, (%eax)
0x00404142:	pushl $0xbc70<UINT32>
0x00404147:	pushl $0x0<UINT8>
0x00404149:	call GlobalAlloc@kernel32.dll
0x0040414c:	movl %ecx, %esp
0x0040414e:	leal %esp, 0xbc70(%eax)
0x00404154:	movl 0x2e(%ecx), %esp
0x00404157:	pushl %ebx
0x00404158:	pushl $0x400000<UINT32>
0x0040415d:	pushl %ecx
0x0040415e:	movl %edi, 0x4(%esp)
0x00404162:	movl %esi, (%ebx)
0x00404164:	addw %di, $0x780<UINT16>
0x00404169:	leal %esi, 0x8(%esi,%ebx)
0x0040416d:	movl (%ebx), %edi
0x0040416f:	pushl %ebx
0x00404170:	movl %ebx, 0x10(%esi)
0x00404173:	movl %eax, $0x880<UINT32>
0x00404178:	pushl %esi
0x00404179:	pushl $0x2<UINT8>
0x0040417b:	pushl %eax
0x0040417c:	pushl %edi
0x0040417d:	pushl $0x6<UINT8>
0x0040417f:	pushl $0xa<UINT8>
0x00404181:	pushl %esi
0x00404182:	pushl $0x4<UINT8>
0x00404184:	pushl %eax
0x00404185:	pushl %edi
0x00404186:	call VirtualProtect@kernel32.dll
VirtualProtect@kernel32.dll: API Node	
0x00404188:	subl %esi, $0x8<UINT8>
0x0040418b:	popl %ecx
0x0040418c:	rep movsl %es:(%edi), %ds:(%esi)
0x0040418e:	popl %ecx
0x0040418f:	addw %di, $0x58<UINT8>
0x00404193:	addl %esi, $0x90<UINT32>
0x00404199:	rep movsl %es:(%edi), %ds:(%esi)
0x0040419b:	call VirtualProtect@kernel32.dll
0x0040419d:	popl %eax
0x0040419e:	leal %ebx, 0x1644(%eax)
0x004041a4:	pushl %eax
0x004041a5:	addb (%esp), $0xc<UINT8>
0x004041a9:	pushl %eax
0x004041aa:	addb (%esp), $0x46<UINT8>
0x004041ae:	pushl %eax
0x004041af:	addb (%esp), $0x65<UINT8>
0x004041b3:	pushl %eax
0x004041b4:	addb (%esp), $0xffffffa1<UINT8>
0x004041b8:	pushl %eax
0x004041b9:	addb (%esp), $0xffffffbf<UINT8>
0x004041bd:	movl %ecx, (%ebx)
0x004041bf:	addl %ebx, $0x14<UINT8>
0x004041c2:	movl %edx, -16(%ebx)
0x004041c5:	testl %edx, %edx
0x004041c7:	je -12
0x004041c9:	movl %eax, 0x18(%esp)
0x004041cd:	leal %esi, (%ecx,%eax)
0x004041d0:	movl %ebp, 0x1c(%esp)
0x004041d4:	movl %ebp, 0x8(%ebp)
0x004041d7:	movl %ecx, -4(%ebx)
0x004041da:	movl %edi, %ebp
0x004041dc:	rep movsl %es:(%edi), %ds:(%esi)
0x004041de:	movl %esi, %ebp
0x004041e0:	movl %edi, -12(%ebx)
0x004041e3:	addl %edi, %eax
0x004041e5:	pushl %ebx
0x004041e6:	pushl %edx
0x004041e7:	pushl %edi
0x004041e8:	pushl %ebp
0x004041e9:	call 0x0040421b
0x0040421b:	pushl %ebp
0x0040421c:	movl %ebp, %esp
0x0040421e:	movb (%edi), %al
0x00404220:	subl %esp, $0xbad8<UINT32>
0x00404226:	leal %ecx, -32888(%ebp)
0x0040422c:	orl -20(%ebp), $0xffffffff<UINT8>
0x00404230:	movl -112(%ebp), %ecx
0x00404233:	leal %ecx, -32888(%ebp)
0x00404239:	movl -116(%ebp), %ecx
0x0040423c:	movl %ecx, 0x8(%ebp)
0x0040423f:	leal %eax, -120(%ebp)
0x00404242:	pushl %ebx
0x00404243:	movl -120(%ebp), %eax
0x00404246:	movl 0x8(%ebp), %ecx
0x00404249:	pushl %esi
0x0040424a:	xorl %eax, %eax
0x0040424c:	xorl %ebx, %ebx
0x0040424e:	leal %ecx, -32888(%ebp)
0x00404254:	pushl %edi
0x00404255:	movl -46680(%ebp), %eax
0x0040425b:	movl -41536(%ebp), %eax
0x00404261:	movl -41532(%ebp), %eax
0x00404267:	movl -8(%ebp), %eax
0x0040426a:	movl -16(%ebp), %ebx
0x0040426d:	xorl %edi, %edi
0x0040426f:	movl -12(%ebp), %ecx
0x00404272:	movl -4(%ebp), $0x8000<UINT32>
0x00404279:	testl %eax, %eax
0x0040427b:	jne 113
0x0040427d:	pushl $0x3<UINT8>
0x0040427f:	popl %esi
0x00404280:	cmpl %edi, %esi
0x00404282:	jae 36
0x00404284:	pushl $0xa<UINT8>
0x00404286:	popl %ecx
0x00404287:	subl %ecx, %edi
0x00404289:	shrl %ecx, $0x3<UINT8>
0x0040428c:	addl -8(%ebp), %ecx
0x0040428f:	movl %ecx, 0x8(%ebp)
0x00404292:	movzbl %edx, (%ecx)
0x00404295:	movl %ecx, %edi
0x00404297:	addl %edi, $0x8<UINT8>
0x0040429a:	shll %edx, %cl
0x0040429c:	orl %ebx, %edx
0x0040429e:	incl 0x8(%ebp)
0x004042a1:	cmpl %edi, %esi
0x004042a3:	jb -22
0x004042a5:	movl -16(%ebp), %ebx
0x004042a8:	movl %ecx, %ebx
0x004042aa:	andl %ecx, $0x7<UINT8>
0x004042ad:	movl %edx, %ecx
0x004042af:	shrl %ecx
0x004042b1:	andl %edx, $0x1<UINT8>
0x004042b4:	subl %ecx, $0x0<UINT8>
0x004042b7:	movl -41540(%ebp), %edx
0x004042bd:	je 19
0x004042bf:	decl %ecx
0x004042c0:	jne 3848
0x004042c6:	shrl %ebx, $0x3<UINT8>
0x004042c9:	movl -16(%ebp), %ebx
0x004042cc:	subl %edi, %esi
0x004042ce:	movl %eax, %esi
0x004042d0:	jmp 0x004042e6
0x004042e6:	movl -46680(%ebp), %eax
0x004042ec:	jmp 0x004042f4
0x004042f4:	cmpl %eax, $0x1<UINT8>
0x004042f7:	jne 0x00404359
0x00404359:	cmpl %eax, $0x2<UINT8>
0x0040435c:	jne 0x0040447f
0x0040447f:	pushl $0x3<UINT8>
0x00404481:	popl %edx
0x00404482:	cmpl %eax, %edx
0x00404484:	jne 81
0x00404486:	cmpl %edi, $0xe<UINT8>
0x00404489:	jae 34
0x0040448b:	pushl $0x15<UINT8>
0x0040448d:	popl %eax
0x0040448e:	subl %eax, %edi
0x00404490:	shrl %eax, $0x3<UINT8>
0x00404493:	addl -8(%ebp), %eax
0x00404496:	movl %eax, 0x8(%ebp)
0x00404499:	movl %ecx, %edi
0x0040449b:	addl %edi, $0x8<UINT8>
0x0040449e:	movzbl %eax, (%eax)
0x004044a1:	shll %eax, %cl
0x004044a3:	orl %ebx, %eax
0x004044a5:	incl 0x8(%ebp)
0x004044a8:	cmpl %edi, $0xe<UINT8>
0x004044ab:	jb 0x00404496
0x004044ad:	movl %ecx, %ebx
0x004044af:	pushl $0x4<UINT8>
0x004044b1:	andl %ecx, $0x3fff<UINT32>
0x004044b7:	subl %edi, $0xe<UINT8>
0x004044ba:	shrl %ebx, $0xe<UINT8>
0x004044bd:	xorl %esi, %esi
0x004044bf:	popl %eax
0x004044c0:	movl -46676(%ebp), %ecx
0x004044c6:	movl -16(%ebp), %ebx
0x004044c9:	movl -46672(%ebp), %esi
0x004044cf:	movl -46680(%ebp), %eax
0x004044d5:	jmp 0x004044df
0x004044df:	cmpl %eax, $0x4<UINT8>
0x004044e2:	jne 250
0x004044e8:	shrl %ecx, $0xa<UINT8>
0x004044eb:	addl %ecx, %eax
0x004044ed:	cmpl -46672(%ebp), %ecx
0x004044f3:	jae 99
0x004044f5:	cmpl %edi, %edx
0x004044f7:	jae 0x0040451a
0x0040451a:	movl %ecx, -46672(%ebp)
0x00404520:	movl %eax, %ebx
0x00404522:	andl %eax, $0x7<UINT8>
0x00404525:	subl %edi, %edx
0x00404527:	addl %ecx, 0xbafc(%esp)
0x0040452e:	movzbl %ecx, (%ecx)
0x00404531:	shrl %ebx, $0x3<UINT8>
0x00404534:	movl -46668(%ebp,%ecx,4), %eax
0x0040453b:	movl %eax, -46676(%ebp)
0x00404541:	incl -46672(%ebp)
0x00404547:	shrl %eax, $0xa<UINT8>
0x0040454a:	addl %eax, $0x4<UINT8>
0x0040454d:	cmpl -46672(%ebp), %eax
0x00404553:	jb 0x004044f5
0x004044f9:	pushl $0xa<UINT8>
0x004044fb:	popl %eax
0x004044fc:	subl %eax, %edi
0x004044fe:	shrl %eax, $0x3<UINT8>
0x00404501:	addl -8(%ebp), %eax
0x00404504:	movl %eax, 0x8(%ebp)
0x00404507:	movl %ecx, %edi
0x00404509:	addl %edi, $0x8<UINT8>
0x0040450c:	movzbl %eax, (%eax)
0x0040450f:	shll %eax, %cl
0x00404511:	orl %ebx, %eax
0x00404513:	incl 0x8(%ebp)
0x00404516:	cmpl %edi, %edx
0x00404518:	jb -22
0x00404555:	movl -16(%ebp), %ebx
0x00404558:	pushl $0x13<UINT8>
0x0040455a:	popl %eax
0x0040455b:	cmpl -46672(%ebp), %eax
0x00404561:	jae 0x00404582
0x00404563:	movl %ecx, -46672(%ebp)
0x00404569:	addl %ecx, 0xbafc(%esp)
0x00404570:	movzbl %ecx, (%ecx)
0x00404573:	movl -46668(%ebp,%ecx,4), %esi
0x0040457a:	incl -46672(%ebp)
0x00404580:	jmp 0x0040455b
0x00404582:	leal %ecx, -108(%ebp)
0x00404585:	movl -41548(%ebp), $0x7<UINT32>
0x0040458f:	pushl %ecx
0x00404590:	leal %ecx, -32(%ebp)
0x00404593:	pushl %ecx
0x00404594:	leal %ecx, -41528(%ebp)
0x0040459a:	pushl %ecx
0x0040459b:	leal %ecx, -41548(%ebp)
0x004045a1:	pushl %ecx
0x004045a2:	leal %ecx, -41544(%ebp)
0x004045a8:	pushl %ecx
0x004045a9:	pushl %esi
0x004045aa:	pushl %esi
0x004045ab:	pushl %eax
0x004045ac:	pushl %eax
0x004045ad:	leal %eax, -46668(%ebp)
0x004045b3:	pushl %eax
0x004045b4:	movl -32(%ebp), %esi
0x004045b7:	call 0x004051da
0x004051da:	pushl %ebp
0x004051db:	movl %ebp, %esp
0x004051dd:	subl %esp, $0xf4<UINT32>
0x004051e3:	movl %ecx, 0x8(%ebp)
0x004051e6:	pushl %ebx
0x004051e7:	pushl %esi
0x004051e8:	pushl %edi
0x004051e9:	movl %edi, 0xc(%ebp)
0x004051ec:	xorl %esi, %esi
0x004051ee:	movl -120(%ebp), %esi
0x004051f1:	movl -116(%ebp), %esi
0x004051f4:	movl -112(%ebp), %esi
0x004051f7:	movl -108(%ebp), %esi
0x004051fa:	movl -104(%ebp), %esi
0x004051fd:	movl -100(%ebp), %esi
0x00405200:	movl -96(%ebp), %esi
0x00405203:	movl -92(%ebp), %esi
0x00405206:	movl -88(%ebp), %esi
0x00405209:	movl -84(%ebp), %esi
0x0040520c:	movl -80(%ebp), %esi
0x0040520f:	movl -76(%ebp), %esi
0x00405212:	movl -72(%ebp), %esi
0x00405215:	movl -68(%ebp), %esi
0x00405218:	movl -64(%ebp), %esi
0x0040521b:	movl -60(%ebp), %esi
0x0040521e:	movl %edx, %edi
0x00405220:	movl %eax, (%ecx)
0x00405222:	addl %ecx, $0x4<UINT8>
0x00405225:	incl -120(%ebp,%eax,4)
0x00405229:	leal %eax, -120(%ebp,%eax,4)
0x0040522d:	decl %edx
0x0040522e:	jne 0x00405220
0x00405230:	cmpl -120(%ebp), %edi
0x00405233:	jne 0x00405246
0x00405246:	movl %ebx, 0x20(%ebp)
0x00405249:	pushl $0x1<UINT8>
0x0040524b:	popl %ecx
0x0040524c:	leal %eax, -116(%ebp)
0x0040524f:	movl %edx, (%ebx)
0x00405251:	movl -4(%ebp), %edx
0x00405254:	cmpl (%eax), %esi
0x00405256:	jne 0x00405261
0x00405258:	incl %ecx
0x00405259:	addl %eax, $0x4<UINT8>
0x0040525c:	cmpl %ecx, $0xf<UINT8>
0x0040525f:	jbe 0x00405254
0x00405261:	cmpl %edx, %ecx
0x00405263:	movl 0x20(%ebp), %ecx
0x00405266:	jae 0x0040526d
0x0040526d:	pushl $0xf<UINT8>
0x0040526f:	leal %edi, -60(%ebp)
0x00405272:	popl %eax
0x00405273:	cmpl (%edi), %esi
0x00405275:	jne 0x0040527f
0x00405277:	decl %eax
0x00405278:	subl %edi, $0x4<UINT8>
0x0040527b:	cmpl %eax, %esi
0x0040527d:	jne 0x00405273
0x0040527f:	cmpl %edx, %eax
0x00405281:	movl -36(%ebp), %eax
0x00405284:	jbe 0x0040528b
0x00405286:	movl -4(%ebp), %eax
0x00405289:	movl %edx, %eax
0x0040528b:	pushl $0x1<UINT8>
0x0040528d:	movl (%ebx), %edx
0x0040528f:	popl %edi
0x00405290:	shll %edi, %cl
0x00405292:	cmpl %ecx, %eax
0x00405294:	jae 0x004052ac
0x00405296:	leal %esi, -120(%ebp,%ecx,4)
0x0040529a:	subl %edi, (%esi)
0x0040529c:	js 722
0x004052a2:	incl %ecx
0x004052a3:	addl %esi, $0x4<UINT8>
0x004052a6:	shll %edi
0x004052a8:	cmpl %ecx, %eax
0x004052aa:	jb 0x0040529a
0x004052ac:	movl %ebx, %eax
0x004052ae:	shll %ebx, $0x2<UINT8>
0x004052b1:	movl %esi, -120(%ebx,%ebp)
0x004052b5:	leal %ecx, -120(%ebx,%ebp)
0x004052b9:	subl %edi, %esi
0x004052bb:	movl -32(%ebp), %edi
0x004052be:	js 688
0x004052c4:	addl %esi, %edi
0x004052c6:	movl (%ecx), %esi
0x004052c8:	xorl %ecx, %ecx
0x004052ca:	decl %eax
0x004052cb:	movl -180(%ebp), %ecx
0x004052d1:	je 0x004052e6
0x004052d3:	xorl %esi, %esi
0x004052d5:	addl %ecx, -116(%esi,%ebp)
0x004052d9:	addl %esi, $0x4<UINT8>
0x004052dc:	decl %eax
0x004052dd:	movl -180(%esi,%ebp), %ecx
0x004052e4:	jne 0x004052d5
0x004052e6:	movl %ecx, 0x8(%ebp)
0x004052e9:	xorl %esi, %esi
0x004052eb:	movl %eax, (%ecx)
0x004052ed:	addl %ecx, $0x4<UINT8>
0x004052f0:	testl %eax, %eax
0x004052f2:	movl 0x8(%ebp), %ecx
0x004052f5:	je 0x00405314
0x004052f7:	movl %ecx, -184(%ebp,%eax,4)
0x004052fe:	movl %edi, 0x2c(%ebp)
0x00405301:	leal %eax, -184(%ebp,%eax,4)
0x00405308:	movl (%edi,%ecx,4), %esi
0x0040530b:	movl %edi, -32(%ebp)
0x0040530e:	incl %ecx
0x0040530f:	movl (%eax), %ecx
0x00405311:	movl %ecx, 0x8(%ebp)
0x00405314:	incl %esi
0x00405315:	cmpl %esi, 0xc(%ebp)
0x00405318:	jb 0x004052eb
0x0040531a:	movl %eax, -184(%ebx,%ebp)
0x00405321:	andl -20(%ebp), $0x0<UINT8>
0x00405325:	andl -184(%ebp), $0x0<UINT8>
0x0040532c:	orl -8(%ebp), $0xffffffff<UINT8>
0x00405330:	movl 0xc(%ebp), %eax
0x00405333:	movl %eax, 0x2c(%ebp)
0x00405336:	movl 0x8(%ebp), %eax
0x00405339:	movl %eax, 0x20(%ebp)
0x0040533c:	movl %ebx, %edx
0x0040533e:	negl %ebx
0x00405340:	cmpl %eax, -36(%ebp)
0x00405343:	jg 529
0x00405349:	leal %eax, -120(%ebp,%eax,4)
0x0040534d:	movl -40(%ebp), %eax
0x00405350:	movl %eax, -40(%ebp)
0x00405353:	movl %eax, (%eax)
0x00405355:	movl %ecx, %eax
0x00405357:	decl %eax
0x00405358:	testl %ecx, %ecx
0x0040535a:	movl -28(%ebp), %eax
0x0040535d:	je 0x00405547
0x00405363:	jmp 0x00405368
0x00405368:	leal %esi, (%edx,%ebx)
0x0040536b:	cmpl 0x20(%ebp), %esi
0x0040536e:	jle 0x00405462
0x00405374:	incl %eax
0x00405375:	movl -24(%ebp), %eax
0x00405378:	incl -8(%ebp)
0x0040537b:	addl %esi, %edx
0x0040537d:	movl -56(%ebp), %esi
0x00405380:	movl %esi, -36(%ebp)
0x00405383:	addl %ebx, %edx
0x00405385:	subl %esi, %ebx
0x00405387:	cmpl %esi, %edx
0x00405389:	jbe 0x0040538d
0x0040538d:	movl %ecx, 0x20(%ebp)
0x00405390:	pushl $0x1<UINT8>
0x00405392:	subl %ecx, %ebx
0x00405394:	popl %eax
0x00405395:	shll %eax, %cl
0x00405397:	cmpl %eax, -24(%ebp)
0x0040539a:	jbe 0x004053ca
0x0040539c:	orl %edi, $0xffffffff<UINT8>
0x0040539f:	subl %edi, -28(%ebp)
0x004053a2:	addl %eax, %edi
0x004053a4:	movl %edi, -40(%ebp)
0x004053a7:	cmpl %ecx, %esi
0x004053a9:	jae 31
0x004053ab:	incl %ecx
0x004053ac:	cmpl %ecx, %esi
0x004053ae:	jae 26
0x004053b0:	jmp 0x004053b5
0x004053b5:	addl %edi, $0x4<UINT8>
0x004053b8:	movl -44(%ebp), %edi
0x004053bb:	movl %edi, (%edi)
0x004053bd:	shll %eax
0x004053bf:	cmpl %eax, %edi
0x004053c1:	jbe 7
0x004053c3:	subl %eax, %edi
0x004053c5:	incl %ecx
0x004053c6:	cmpl %ecx, %esi
0x004053c8:	jb 0x004053b2
0x004053b2:	movl %edi, -44(%ebp)
0x004053ca:	movl %eax, 0x28(%ebp)
0x004053cd:	pushl $0x1<UINT8>
0x004053cf:	popl %esi
0x004053d0:	movl %eax, (%eax)
0x004053d2:	shll %esi, %cl
0x004053d4:	movl -44(%ebp), %esi
0x004053d7:	addl %esi, %eax
0x004053d9:	cmpl %esi, $0x5a0<UINT32>
0x004053df:	ja 399
0x004053e5:	movl %edi, 0x24(%ebp)
0x004053e8:	leal %eax, (%eax,%eax,2)
0x004053eb:	leal %edi, (%edi,%eax,2)
0x004053ee:	movl %eax, -8(%ebp)
0x004053f1:	shll %eax, $0x2<UINT8>
0x004053f4:	cmpl -8(%ebp), $0x0<UINT8>
0x004053f8:	movl -52(%ebp), %eax
0x004053fb:	leal %eax, -244(%eax,%ebp)
0x00405402:	movl -48(%ebp), %edi
0x00405405:	movl (%eax), %edi
0x00405407:	movl %edi, 0x28(%ebp)
0x0040540a:	movl (%edi), %esi
0x0040540c:	je 0x0040544b
0x0040544b:	movl %eax, 0x1c(%ebp)
0x0040544e:	movl %ecx, -48(%ebp)
0x00405451:	movl (%eax), %ecx
0x00405453:	movl %esi, -56(%ebp)
0x00405456:	movl %edi, -32(%ebp)
0x00405459:	cmpl 0x20(%ebp), %esi
0x0040545c:	jg -234
0x00405462:	movb %al, 0x20(%ebp)
0x00405465:	movl %ecx, 0x2c(%ebp)
0x00405468:	subb %al, %bl
0x0040546a:	movb -15(%ebp), %al
0x0040546d:	movl %eax, 0xc(%ebp)
0x00405470:	leal %eax, (%ecx,%eax,4)
0x00405473:	cmpl 0x8(%ebp), %eax
0x00405476:	jb 0x0040547e
0x0040547e:	movl %eax, 0x8(%ebp)
0x00405481:	movl %eax, (%eax)
0x00405483:	cmpl %eax, 0x10(%ebp)
0x00405486:	jae 0x0040549a
0x00405488:	cmpl %eax, $0x100<UINT32>
0x0040548d:	sbbl %ecx, %ecx
0x0040548f:	andl %ecx, $0xffffffa0<UINT8>
0x00405492:	addl %ecx, $0x60<UINT8>
0x00405495:	movb -16(%ebp), %cl
0x00405498:	jmp 0x004054b0
0x004054b0:	addl 0x8(%ebp), $0x4<UINT8>
0x004054b4:	movl -14(%ebp), %eax
0x004054b7:	movl %ecx, 0x20(%ebp)
0x004054ba:	movl %esi, -20(%ebp)
0x004054bd:	pushl $0x1<UINT8>
0x004054bf:	subl %ecx, %ebx
0x004054c1:	popl %edx
0x004054c2:	movl %eax, %esi
0x004054c4:	shll %edx, %cl
0x004054c6:	movl %ecx, %ebx
0x004054c8:	shrl %eax, %cl
0x004054ca:	cmpl %eax, -44(%ebp)
0x004054cd:	jae 42
0x004054cf:	movl %edi, -48(%ebp)
0x004054d2:	leal %esi, (%eax,%eax,2)
0x004054d5:	leal %ecx, (%edx,%edx,2)
0x004054d8:	leal %esi, (%edi,%esi,2)
0x004054db:	shll %ecx
0x004054dd:	movl -24(%ebp), %esi
0x004054e0:	movl %edi, -24(%ebp)
0x004054e3:	addl -24(%ebp), %ecx
0x004054e6:	leal %esi, -16(%ebp)
0x004054e9:	addl %eax, %edx
0x004054eb:	cmpl %eax, -44(%ebp)
0x004054ee:	movsl %es:(%edi), %ds:(%esi)
0x004054ef:	movsw %es:(%edi), %ds:(%esi)
0x004054f1:	jb 0x004054e0
0x004054f3:	movl %edi, -32(%ebp)
0x004054f6:	movl %esi, -20(%ebp)
0x004054f9:	movl %eax, 0x20(%ebp)
0x004054fc:	pushl $0x1<UINT8>
0x004054fe:	leal %ecx, -1(%eax)
0x00405501:	popl %eax
0x00405502:	shll %eax, %cl
0x00405504:	testl %esi, %eax
0x00405506:	je 0x0040550e
0x0040550e:	xorl %esi, %eax
0x00405510:	movl %eax, -8(%ebp)
0x00405513:	movl -20(%ebp), %esi
0x00405516:	leal %eax, -184(%ebp,%eax,4)
0x0040551d:	pushl $0x1<UINT8>
0x0040551f:	movl %ecx, %ebx
0x00405521:	popl %edx
0x00405522:	shll %edx, %cl
0x00405524:	decl %edx
0x00405525:	andl %edx, %esi
0x00405527:	cmpl %edx, (%eax)
0x00405529:	je 0x00405536
0x00405536:	movl %eax, -28(%ebp)
0x00405539:	decl -28(%ebp)
0x0040553c:	movl %edx, -4(%ebp)
0x0040553f:	testl %eax, %eax
0x00405541:	jne 0x00405365
0x00405365:	movl %eax, -28(%ebp)
0x00405508:	xorl %esi, %eax
0x0040550a:	shrl %eax
0x0040550c:	jmp 0x00405504
0x00405547:	incl 0x20(%ebp)
0x0040554a:	addl -40(%ebp), $0x4<UINT8>
0x0040554e:	movl %eax, 0x20(%ebp)
0x00405551:	cmpl %eax, -36(%ebp)
0x00405554:	jle 0x00405350
0x0040555a:	testl %edi, %edi
0x0040555c:	je 0x0040523f
0x0040523f:	xorl %eax, %eax
0x00405241:	popl %edi
0x00405242:	popl %esi
0x00405243:	popl %ebx
0x00405244:	leave
0x00405245:	ret

0x004045bc:	addl %esp, $0x28<UINT8>
0x004045bf:	testl %eax, %eax
0x004045c1:	jne 3079
0x004045c7:	cmpl -41548(%ebp), %esi
0x004045cd:	je 3067
0x004045d3:	pushl $0x5<UINT8>
0x004045d5:	movl -46672(%ebp), %esi
0x004045db:	popl %eax
0x004045dc:	movl -46680(%ebp), %eax
0x004045e2:	cmpl %eax, $0x5<UINT8>
0x004045e5:	jne 585
0x004045eb:	movl %eax, -46676(%ebp)
0x004045f1:	movl %ecx, -46676(%ebp)
0x004045f7:	shrl %eax, $0x5<UINT8>
0x004045fa:	andl %eax, $0x1f<UINT8>
0x004045fd:	andl %ecx, $0x1f<UINT8>
0x00404600:	leal %eax, 0x102(%ecx,%eax)
0x00404607:	cmpl -46672(%ebp), %eax
0x0040460d:	movl -28(%ebp), %eax
0x00404610:	jae 0x0040473b
0x00404616:	movl %edx, -41548(%ebp)
0x0040461c:	cmpl %edi, %edx
0x0040461e:	jae 0x00404644
0x00404620:	movl %eax, %edx
0x00404622:	subl %eax, %edi
0x00404624:	addl %eax, $0x7<UINT8>
0x00404627:	shrl %eax, $0x3<UINT8>
0x0040462a:	addl -8(%ebp), %eax
0x0040462d:	movl %eax, 0x8(%ebp)
0x00404630:	movl %ecx, %edi
0x00404632:	addl %edi, $0x8<UINT8>
0x00404635:	movzbl %eax, (%eax)
0x00404638:	shll %eax, %cl
0x0040463a:	orl -16(%ebp), %eax
0x0040463d:	incl 0x8(%ebp)
0x00404640:	cmpl %edi, %edx
0x00404642:	jb -23
0x00404644:	pushl $0x1<UINT8>
0x00404646:	movl %ecx, %edx
0x00404648:	popl %eax
0x00404649:	shll %eax, %cl
0x0040464b:	movl %ecx, -41544(%ebp)
0x00404651:	decl %eax
0x00404652:	andl %eax, -16(%ebp)
0x00404655:	leal %eax, (%eax,%eax,2)
0x00404658:	movl %esi, 0x2(%ecx,%eax,2)
0x0040465c:	leal %ecx, (%ecx,%eax,2)
0x0040465f:	cmpl %esi, $0x10<UINT8>
0x00404662:	movzbl %eax, 0x1(%ecx)
0x00404666:	jae 0x00404687
0x00404668:	movl %ecx, %eax
0x0040466a:	subl %edi, %eax
0x0040466c:	movl %eax, -46672(%ebp)
0x00404672:	shrl -16(%ebp), %cl
0x00404675:	movl -46668(%ebp,%eax,4), %esi
0x0040467c:	incl -46672(%ebp)
0x00404682:	jmp 0x004045eb
0x00404687:	cmpl %esi, $0x12<UINT8>
0x0040468a:	jne 0x00404691
0x0040468c:	pushl $0x7<UINT8>
0x0040468e:	popl %edx
0x0040468f:	jmp 0x00404694
0x00404694:	xorl %ecx, %ecx
0x00404696:	cmpl %esi, $0x12<UINT8>
0x00404699:	setne %cl
0x0040469c:	decl %ecx
0x0040469d:	andl %ecx, $0x8<UINT8>
0x004046a0:	addl %ecx, $0x3<UINT8>
0x004046a3:	movl -24(%ebp), %ecx
0x004046a6:	leal %ecx, (%eax,%edx)
0x004046a9:	cmpl %edi, %ecx
0x004046ab:	jae 0x004046d2
0x004046ad:	subl %ecx, %edi
0x004046af:	addl %ecx, $0x7<UINT8>
0x004046b2:	shrl %ecx, $0x3<UINT8>
0x004046b5:	addl -8(%ebp), %ecx
0x004046b8:	movl %ecx, 0x8(%ebp)
0x004046bb:	movzbl %ebx, (%ecx)
0x004046be:	movl %ecx, %edi
0x004046c0:	addl %edi, $0x8<UINT8>
0x004046c3:	shll %ebx, %cl
0x004046c5:	leal %ecx, (%eax,%edx)
0x004046c8:	orl -16(%ebp), %ebx
0x004046cb:	incl 0x8(%ebp)
0x004046ce:	cmpl %edi, %ecx
0x004046d0:	jb -26
0x004046d2:	movl %ecx, %eax
0x004046d4:	pushl $0x1<UINT8>
0x004046d6:	shrl -16(%ebp), %cl
0x004046d9:	popl %ebx
0x004046da:	movl %ecx, %edx
0x004046dc:	shll %ebx, %cl
0x004046de:	addl %edx, %eax
0x004046e0:	movl %eax, -46672(%ebp)
0x004046e6:	subl %edi, %edx
0x004046e8:	decl %ebx
0x004046e9:	andl %ebx, -16(%ebp)
0x004046ec:	shrl -16(%ebp), %cl
0x004046ef:	addl -24(%ebp), %ebx
0x004046f2:	movl %edx, -24(%ebp)
0x004046f5:	movl %ebx, %eax
0x004046f7:	leal %ecx, (%eax,%edx)
0x004046fa:	cmpl %ecx, -28(%ebp)
0x004046fd:	ja 2763
0x00404703:	cmpl %esi, $0x10<UINT8>
0x00404706:	jne 0x0040471e
0x0040471e:	xorl %ecx, %ecx
0x00404720:	leal %eax, -46668(%ebp,%eax,4)
0x00404727:	movl (%eax), %ecx
0x00404729:	incl %ebx
0x0040472a:	addl %eax, $0x4<UINT8>
0x0040472d:	decl %edx
0x0040472e:	jne 0x00404727
0x00404730:	movl -46672(%ebp), %ebx
0x00404736:	jmp 0x004045eb
0x00404691:	leal %edx, -14(%esi)
0x0040473b:	leal %eax, -47832(%ebp)
0x00404741:	movl %ebx, -46676(%ebp)
0x00404747:	pushl %eax
0x00404748:	leal %eax, -28(%ebp)
0x0040474b:	pushl %eax
0x0040474c:	leal %eax, -41528(%ebp)
0x00404752:	pushl %eax
0x00404753:	leal %eax, -24(%ebp)
0x00404756:	pushl %eax
0x00404757:	movl %esi, %ebx
0x00404759:	leal %eax, -46658(%ebp)
0x0040475f:	andl %ebx, $0x1f<UINT8>
0x00404762:	andl -41544(%ebp), $0x0<UINT8>
0x00404769:	andl -28(%ebp), $0x0<UINT8>
0x0040476d:	pushl %eax
0x0040476e:	pushl 0xbb1c(%esp)
0x00404775:	pushl 0xbb24(%esp)
0x0040477c:	leal %eax, 0x101(%ebx)
0x00404782:	pushl $0x101<UINT32>
0x00404787:	pushl %eax
0x00404788:	leal %eax, -46668(%ebp)
0x0040478e:	movl -24(%ebp), $0x9<UINT32>
0x00404795:	pushl %eax
0x00404796:	movl -32(%ebp), $0x6<UINT32>
0x0040479d:	call 0x004051da
0x0040549a:	subl %eax, 0x10(%ebp)
0x0040549d:	movl %ecx, 0x18(%ebp)
0x004054a0:	movb %cl, (%ecx,%eax)
0x004054a3:	addb %cl, $0x50<UINT8>
0x004054a6:	movb -16(%ebp), %cl
0x004054a9:	movl %ecx, 0x14(%ebp)
0x004054ac:	movzwl %eax, (%ecx,%eax,2)
0x004047a2:	addl %esp, $0x28<UINT8>
0x004047a5:	testl %eax, %eax
0x004047a7:	jne 2593
0x004047ad:	cmpl -24(%ebp), %eax
0x004047b0:	je 2584
0x004047b6:	leal %eax, -47832(%ebp)
0x004047bc:	pushl %eax
0x004047bd:	leal %eax, -28(%ebp)
0x004047c0:	pushl %eax
0x004047c1:	leal %eax, -41528(%ebp)
0x004047c7:	pushl %eax
0x004047c8:	leal %eax, -32(%ebp)
0x004047cb:	pushl %eax
0x004047cc:	leal %eax, -46654(%ebp)
0x004047d2:	shrl %esi, $0x5<UINT8>
0x004047d5:	pushl %eax
0x004047d6:	andl %esi, $0x1f<UINT8>
0x004047d9:	pushl 0xbb14(%esp)
0x004047e0:	pushl 0xbb1c(%esp)
0x004047e7:	incl %esi
0x004047e8:	pushl $0x0<UINT8>
0x004047ea:	leal %eax, -45640(%ebp,%ebx,4)
0x004047f1:	pushl %esi
0x004047f2:	pushl %eax
0x004047f3:	call 0x004051da
0x004047f8:	addl %esp, $0x28<UINT8>
0x004047fb:	testl %eax, %eax
0x004047fd:	jne 2507
0x00404803:	movl %eax, -32(%ebp)
0x00404806:	testl %eax, %eax
0x00404808:	jne 0x00404812
0x00404812:	movb %cl, -24(%ebp)
0x00404815:	andl -46676(%ebp), $0x0<UINT8>
0x0040481c:	movl %ebx, -16(%ebp)
0x0040481f:	pushl $0x6<UINT8>
0x00404821:	movb -46659(%ebp), %al
0x00404827:	popl %eax
0x00404828:	movb -46660(%ebp), %cl
0x0040482e:	movl -46680(%ebp), %eax
0x00404834:	cmpl %eax, $0x6<UINT8>
0x00404837:	jne 2288
0x0040483d:	movl %eax, -12(%ebp)
0x00404840:	movl %edx, -116(%ebp)
0x00404843:	movl %ecx, 0x8(%ebp)
0x00404846:	andl -8(%ebp), $0x0<UINT8>
0x0040484a:	movl %esi, %edi
0x0040484c:	movl -41536(%ebp), %edi
0x00404852:	cmpl %eax, %edx
0x00404854:	movl -41532(%ebp), %ebx
0x0040485a:	movl -112(%ebp), %eax
0x0040485d:	movl -4(%ebp), %ecx
0x00404860:	movl 0x8(%ebp), %ebx
0x00404863:	movl -12(%ebp), %esi
0x00404866:	movl %edi, %eax
0x00404868:	jae 0x0040486f
0x0040486f:	movl %edx, -120(%ebp)
0x00404872:	subl %edx, %eax
0x00404874:	cmpl -46676(%ebp), $0x0<UINT8>
0x0040487b:	jne 0x00404b88
0x00404881:	cmpl %edx, $0x102<UINT32>
0x00404887:	jb 725
0x0040488d:	movl %eax, -12(%ebp)
0x00404890:	movl %ecx, -116(%ebp)
0x00404893:	movl %esi, 0x8(%ebp)
0x00404896:	movl %ebx, -4(%ebp)
0x00404899:	andl -12(%ebp), $0x0<UINT8>
0x0040489d:	cmpl %edi, %ecx
0x0040489f:	movl -41532(%ebp), %esi
0x004048a5:	movl -41536(%ebp), %eax
0x004048ab:	movl -112(%ebp), %edi
0x004048ae:	movl -4(%ebp), %ebx
0x004048b1:	jae 0x004048b8
0x004048b8:	movl %ecx, -120(%ebp)
0x004048bb:	subl %ecx, %edi
0x004048bd:	pushl $0x1<UINT8>
0x004048bf:	movl -16(%ebp), %ecx
0x004048c2:	movb %cl, -46660(%ebp)
0x004048c8:	popl %edx
0x004048c9:	shll %edx, %cl
0x004048cb:	movb %cl, -46659(%ebp)
0x004048d1:	pushl $0x1<UINT8>
0x004048d3:	decl %edx
0x004048d4:	movl -32(%ebp), %edx
0x004048d7:	popl %edx
0x004048d8:	shll %edx, %cl
0x004048da:	decl %edx
0x004048db:	movl -28(%ebp), %edx
0x004048de:	cmpl %eax, $0x14<UINT8>
0x004048e1:	jae 0x00404903
0x004048e3:	pushl $0x1b<UINT8>
0x004048e5:	popl %ecx
0x004048e6:	subl %ecx, %eax
0x004048e8:	shrl %ecx, $0x3<UINT8>
0x004048eb:	addl -12(%ebp), %ecx
0x004048ee:	movzbl %edx, (%ebx)
0x004048f1:	movl %ecx, %eax
0x004048f3:	addl %eax, $0x8<UINT8>
0x004048f6:	shll %edx, %cl
0x004048f8:	orl %esi, %edx
0x004048fa:	incl %ebx
0x004048fb:	cmpl %eax, $0x14<UINT8>
0x004048fe:	jb 0x004048ee
0x00404900:	movl -4(%ebp), %ebx
0x00404903:	movl %ecx, -32(%ebp)
0x00404906:	movl %edx, -46658(%ebp)
0x0040490c:	andl %ecx, %esi
0x0040490e:	leal %ecx, (%ecx,%ecx,2)
0x00404911:	leal %edx, (%edx,%ecx,2)
0x00404914:	movzbl %ecx, (%edx)
0x00404917:	movl 0x8(%ebp), %ecx
0x0040491a:	testl %ecx, %ecx
0x0040491c:	movzbl %ecx, 0x1(%edx)
0x00404920:	jne 0x00404938
0x00404922:	shrl %esi, %cl
0x00404924:	movzbl %ecx, 0x1(%edx)
0x00404928:	subl %eax, %ecx
0x0040492a:	movb %cl, 0x2(%edx)
0x0040492d:	movb (%edi), %cl
0x0040492f:	incl %edi
0x00404930:	decl -16(%ebp)
0x00404933:	jmp 0x00404ab4
0x00404ab4:	cmpl -16(%ebp), $0x102<UINT32>
0x00404abb:	jae 0x004048de
0x00404938:	shrl %esi, %cl
0x0040493a:	movzbl %ecx, 0x1(%edx)
0x0040493e:	subl %eax, %ecx
0x00404940:	testb 0x8(%ebp), $0x10<UINT8>
0x00404944:	jne 0x00404985
0x00404985:	andl 0x8(%ebp), $0xf<UINT8>
0x00404989:	pushl $0x1<UINT8>
0x0040498b:	movl %ecx, 0x8(%ebp)
0x0040498e:	popl %ebx
0x0040498f:	shll %ebx, %cl
0x00404991:	subl %eax, %ecx
0x00404993:	decl %ebx
0x00404994:	andl %ebx, %esi
0x00404996:	addl %ebx, 0x2(%edx)
0x00404999:	shrl %esi, %cl
0x0040499b:	cmpl %eax, $0xf<UINT8>
0x0040499e:	movl -8(%ebp), %ebx
0x004049a1:	jae 0x004049c5
0x004049c5:	movl %ecx, -28(%ebp)
0x004049c8:	movl %edx, -46654(%ebp)
0x004049ce:	andl %ecx, %esi
0x004049d0:	leal %ecx, (%ecx,%ecx,2)
0x004049d3:	leal %edx, (%edx,%ecx,2)
0x004049d6:	movzbl %ecx, (%edx)
0x004049d9:	movl 0x8(%ebp), %ecx
0x004049dc:	movzbl %ecx, 0x1(%edx)
0x004049e0:	shrl %esi, %cl
0x004049e2:	subl %eax, %ecx
0x004049e4:	testb 0x8(%ebp), $0x10<UINT8>
0x004049e8:	jne 0x00404a07
0x00404a07:	andl 0x8(%ebp), $0xf<UINT8>
0x00404a0b:	cmpl %eax, 0x8(%ebp)
0x00404a0e:	jae 0x00404a35
0x00404a35:	movl %ecx, 0x8(%ebp)
0x00404a38:	pushl $0x1<UINT8>
0x00404a3a:	popl %ebx
0x00404a3b:	subl %eax, %ecx
0x00404a3d:	shll %ebx, %cl
0x00404a3f:	decl %ebx
0x00404a40:	andl %ebx, %esi
0x00404a42:	addl %ebx, 0x2(%edx)
0x00404a45:	leal %edx, -32888(%ebp)
0x00404a4b:	shrl %esi, %cl
0x00404a4d:	movl %ecx, -8(%ebp)
0x00404a50:	subl -16(%ebp), %ecx
0x00404a53:	movl %ecx, %edi
0x00404a55:	subl %ecx, %edx
0x00404a57:	cmpl %ecx, %ebx
0x00404a59:	jb 23
0x00404a5b:	movl %ecx, %edi
0x00404a5d:	subl %ecx, %ebx
0x00404a5f:	movb %dl, (%ecx)
0x00404a61:	movb (%edi), %dl
0x00404a63:	movb %dl, 0x1(%ecx)
0x00404a66:	incl %edi
0x00404a67:	incl %ecx
0x00404a68:	movb (%edi), %dl
0x00404a6a:	incl %edi
0x00404a6b:	incl %ecx
0x00404a6c:	subl -8(%ebp), $0x2<UINT8>
0x00404a70:	jmp 0x00404a97
0x00404a97:	movb %dl, (%ecx)
0x00404a99:	movb (%edi), %dl
0x00404a9b:	incl %edi
0x00404a9c:	incl %ecx
0x00404a9d:	decl -8(%ebp)
0x00404aa0:	jne 0x00404a97
0x00404aa2:	jmp 0x00404ab1
0x00404ab1:	movl %ebx, -4(%ebp)
0x00404946:	jmp 0x0040494b
0x0040494b:	testb 0x8(%ebp), $0x40<UINT8>
0x0040494f:	jne 0x00404aec
0x00404aec:	testb 0x8(%ebp), $0x20<UINT8>
0x00404af0:	je 1752
0x00404af6:	movl %ecx, %eax
0x00404af8:	shrl %ecx, $0x3<UINT8>
0x00404afb:	cmpl %ecx, -12(%ebp)
0x00404afe:	jb 0x00404b03
0x00404b03:	movl %edx, %ecx
0x00404b05:	movl -41532(%ebp), %esi
0x00404b0b:	shll %edx, $0x3<UINT8>
0x00404b0e:	subl %eax, %edx
0x00404b10:	subl %ebx, %ecx
0x00404b12:	movl -41536(%ebp), %eax
0x00404b18:	movl -112(%ebp), %edi
0x00404b1b:	movl -20(%ebp), $0x1<UINT32>
0x00404b22:	movl %edx, -116(%ebp)
0x00404b25:	andl -8(%ebp), $0x0<UINT8>
0x00404b29:	cmpl %edi, %edx
0x00404b2b:	movl -4(%ebp), %ebx
0x00404b2e:	movl 0x8(%ebp), %esi
0x00404b31:	movl -12(%ebp), %eax
0x00404b34:	jae 0x00404b3b
0x00404b3b:	movl %edx, -120(%ebp)
0x00404b3e:	subl %edx, %edi
0x00404b40:	cmpl -20(%ebp), $0x0<UINT8>
0x00404b44:	je 28
0x00404b46:	cmpl -20(%ebp), $0x1<UINT8>
0x00404b4a:	jne 1662
0x00404b50:	movl %esi, -12(%ebp)
0x00404b53:	movl -46676(%ebp), $0x7<UINT32>
0x00404b5d:	jmp 0x00404874
0x00404b88:	cmpl -46676(%ebp), $0x1<UINT8>
0x00404b8f:	jne 0x00404c4c
0x00404c4c:	cmpl -46676(%ebp), $0x2<UINT8>
0x00404c53:	jne 0x00404cc1
0x00404cc1:	movl %ebx, -46668(%ebp)
0x00404cc7:	cmpl -46676(%ebp), $0x3<UINT8>
0x00404cce:	jne 0x00404d76
0x00404d76:	cmpl -46676(%ebp), $0x4<UINT8>
0x00404d7d:	jne 0x00404dca
0x00404dca:	cmpl -46676(%ebp), $0x5<UINT8>
0x00404dd1:	jne 0x00404f01
0x00404f01:	cmpl -46676(%ebp), $0x6<UINT8>
0x00404f08:	jne 0x00404fdd
0x00404fdd:	cmpl -46676(%ebp), $0x7<UINT8>
0x00404fe4:	jne 70
0x00404fe6:	leal %eax, 0x10(%ebp)
0x00404fe9:	movl -112(%ebp), %edi
0x00404fec:	pushl %eax
0x00404fed:	leal %eax, 0xc(%ebp)
0x00404ff0:	pushl %eax
0x00404ff1:	leal %eax, -46680(%ebp)
0x00404ff7:	pushl %eax
0x00404ff8:	leal %eax, -20(%ebp)
0x00404ffb:	pushl %eax
0x00404ffc:	call 0x0040557c
0x0040557c:	pushl %ebp
0x0040557d:	movl %ebp, %esp
0x0040557f:	pushl %ecx
0x00405580:	movl %eax, 0x10(%ebp)
0x00405583:	pushl %ebx
0x00405584:	pushl %esi
0x00405585:	movl %esi, 0xc(%ebp)
0x00405588:	movl %edx, (%eax)
0x0040558a:	pushl %edi
0x0040558b:	movl %ecx, 0xb5e4(%esi)
0x00405591:	movl %edi, 0xb5e8(%esi)
0x00405597:	cmpl %ecx, %edi
0x00405599:	movl -4(%ebp), %edx
0x0040559c:	movl 0xc(%ebp), %ecx
0x0040559f:	jbe 0x004055a7
0x004055a7:	movl %ebx, 0x14(%ebp)
0x004055aa:	subl %edi, %ecx
0x004055ac:	movl %eax, (%ebx)
0x004055ae:	cmpl %edi, %eax
0x004055b0:	jbe 0x004055b4
0x004055b4:	testl %edi, %edi
0x004055b6:	je 0x004055c3
0x004055b8:	movl %eax, 0x8(%ebp)
0x004055bb:	cmpl (%eax), $0xffffffff<UINT8>
0x004055be:	jne 0x004055c3
0x004055c3:	subl (%ebx), %edi
0x004055c5:	pushl %edi
0x004055c6:	pushl %ecx
0x004055c7:	pushl %edx
0x004055c8:	call 0x004041fc
0x004041fc:	pushl %esi
0x004041fd:	pushl %edi
0x004041fe:	movl %edi, 0xc(%esp)
0x00404202:	movl %esi, 0x10(%esp)
0x00404206:	movl %ecx, 0x14(%esp)
0x0040420a:	sarl %ecx, $0x2<UINT8>
0x0040420d:	rep movsl %es:(%edi), %ds:(%esi)
0x0040420f:	movl %ecx, 0x14(%esp)
0x00404213:	andl %ecx, $0x3<UINT8>
0x00404216:	rep movsb %es:(%edi), %ds:(%esi)
0x00404218:	popl %edi
0x00404219:	popl %esi
0x0040421a:	ret

0x004055cd:	addl 0xc(%ebp), %edi
0x004055d0:	movl %eax, 0xb5e0(%esi)
0x004055d6:	addl -4(%ebp), %edi
0x004055d9:	addl %esp, $0xc<UINT8>
0x004055dc:	cmpl 0xc(%ebp), %eax
0x004055df:	jne 0x0040562c
0x0040562c:	movl %eax, 0x10(%ebp)
0x0040562f:	movl %ecx, -4(%ebp)
0x00405632:	popl %edi
0x00405633:	movl (%eax), %ecx
0x00405635:	movl %eax, 0xc(%ebp)
0x00405638:	movl 0xb5e4(%esi), %eax
0x0040563e:	popl %esi
0x0040563f:	popl %ebx
0x00405640:	leave
0x00405641:	ret

0x00405001:	movl %edi, -112(%ebp)
0x00405004:	addl %esp, $0x10<UINT8>
0x00405007:	cmpl %edi, -116(%ebp)
0x0040500a:	jae 0x00405014
0x00405014:	movl %edx, -120(%ebp)
0x00405017:	subl %edx, %edi
0x00405019:	cmpl -116(%ebp), %edi
0x0040501c:	jne 428
0x00405022:	movl -46676(%ebp), $0x8<UINT32>
0x0040502c:	cmpl -46676(%ebp), $0x8<UINT8>
0x00405033:	je 0x00405087
0x00405087:	movl %eax, 0x8(%ebp)
0x0040508a:	pushl $0x1<UINT8>
0x0040508c:	movl -41532(%ebp), %eax
0x00405092:	popl %ebx
0x00405093:	leal %eax, 0x10(%ebp)
0x00405096:	movl -20(%ebp), %ebx
0x00405099:	pushl %eax
0x0040509a:	leal %eax, 0xc(%ebp)
0x0040509d:	pushl %eax
0x0040509e:	leal %eax, -46680(%ebp)
0x004050a4:	pushl %eax
0x004050a5:	leal %eax, -20(%ebp)
0x004050a8:	pushl %eax
0x004050a9:	movl -41536(%ebp), %esi
0x004050af:	movl -112(%ebp), %edi
0x004050b2:	call 0x0040557c
0x004050b7:	addl %esp, $0x10<UINT8>
0x004050ba:	cmpl -20(%ebp), %ebx
0x004050bd:	jne 267
0x004050c3:	movl %eax, -41532(%ebp)
0x004050c9:	movl %ecx, -116(%ebp)
0x004050cc:	movl %edx, -4(%ebp)
0x004050cf:	movl %edi, -41536(%ebp)
0x004050d5:	movl -16(%ebp), %eax
0x004050d8:	movl %eax, -112(%ebp)
0x004050db:	xorl %esi, %esi
0x004050dd:	cmpl %eax, %ecx
0x004050df:	movl -20(%ebp), %esi
0x004050e2:	movl 0x8(%ebp), %edx
0x004050e5:	movl -8(%ebp), %esi
0x004050e8:	movl -12(%ebp), %eax
0x004050eb:	jae 0x004050f2
0x004050f2:	movl %ecx, -120(%ebp)
0x004050f5:	subl %ecx, %eax
0x004050f7:	cmpl -41540(%ebp), %esi
0x004050fd:	movl -4(%ebp), %ecx
0x00405100:	jne 0x00405112
0x00405112:	pushl $0x7<UINT8>
0x00405114:	popl %eax
0x00405115:	cmpl %edi, %eax
0x00405117:	jbe 0x00405124
0x00405124:	movl %ebx, -16(%ebp)
0x00405127:	movl -46680(%ebp), %eax
0x0040512d:	cmpl %eax, $0x7<UINT8>
0x00405130:	jne 74
0x00405132:	movl %eax, -12(%ebp)
0x00405135:	movl -112(%ebp), %eax
0x00405138:	leal %eax, 0x10(%ebp)
0x0040513b:	pushl %eax
0x0040513c:	leal %eax, 0xc(%ebp)
0x0040513f:	pushl %eax
0x00405140:	leal %eax, -46680(%ebp)
0x00405146:	pushl %eax
0x00405147:	leal %eax, -20(%ebp)
0x0040514a:	pushl %eax
0x0040514b:	call 0x0040557c
0x00405150:	movl %eax, -112(%ebp)
0x00405153:	addl %esp, $0x10<UINT8>
0x00405156:	cmpl %eax, -116(%ebp)
0x00405159:	movl -12(%ebp), %eax
0x0040515c:	jae 0x00405166
0x00405166:	movl %ecx, -120(%ebp)
0x00405169:	subl %ecx, %eax
0x0040516b:	cmpl -116(%ebp), %eax
0x0040516e:	movl -4(%ebp), %ecx
0x00405171:	jne 19
0x00405173:	pushl $0x8<UINT8>
0x00405175:	popl %eax
0x00405176:	movl -46680(%ebp), %eax
0x0040517c:	cmpl %eax, $0x8<UINT8>
0x0040517f:	je 0x00405194
0x00405194:	movl -20(%ebp), $0x1<UINT32>
0x0040519b:	movl %eax, -12(%ebp)
0x0040519e:	movl -41532(%ebp), %ebx
0x004051a4:	movl -41536(%ebp), %edi
0x004051aa:	movl -112(%ebp), %eax
0x004051ad:	leal %eax, 0x10(%ebp)
0x004051b0:	pushl %eax
0x004051b1:	leal %eax, 0xc(%ebp)
0x004051b4:	pushl %eax
0x004051b5:	leal %eax, -46680(%ebp)
0x004051bb:	pushl %eax
0x004051bc:	leal %eax, -20(%ebp)
0x004051bf:	pushl %eax
0x004051c0:	call 0x0040557c
0x004051c5:	addl %esp, $0x10<UINT8>
0x004051c8:	cmpl -20(%ebp), $0x0<UINT8>
0x004051cc:	jnl 0x004051d2
0x004051d2:	movl %eax, 0x8(%ebp)
0x004051d5:	popl %edi
0x004051d6:	popl %esi
0x004051d7:	popl %ebx
0x004051d8:	leave
0x004051d9:	ret

0x004041ee:	testl %eax, %eax
0x004041f0:	je -292
0x004041f6:	popl %edx
0x004041f7:	popl %eax
0x004041f8:	popl %ecx
0x004041f9:	popl %ebx
0x004041fa:	jmp 0x004041bd
0x00404708:	cmpl %eax, $0x1<UINT8>
0x0040470b:	jb 2749
0x00404711:	cmpl %esi, %esi
0x00404713:	jne 9
0x00404715:	movl %ecx, -46672(%ebp,%eax,4)
0x0040471c:	jmp 0x00404720
0x0040538b:	movl %esi, %edx
0x0040540e:	movl %edi, -52(%ebp)
0x00405411:	movl %esi, -20(%ebp)
0x00405414:	movb -16(%ebp), %cl
0x00405417:	movl %ecx, %ebx
0x00405419:	subl %ecx, %edx
0x0040541b:	movl -184(%edi,%ebp), %esi
0x00405422:	shrl %esi, %cl
0x00405424:	movl %ecx, -4(%eax)
0x00405427:	movl %eax, -48(%ebp)
0x0040542a:	subl %eax, %ecx
0x0040542c:	movb -15(%ebp), %dl
0x0040542f:	pushl $0x6<UINT8>
0x00405431:	cltd
0x00405432:	popl %edi
0x00405433:	idivl %eax, %edi
0x00405435:	movl %edx, -4(%ebp)
0x00405438:	subl %eax, %esi
0x0040543a:	movl -14(%ebp), %eax
0x0040543d:	leal %eax, (%esi,%esi,2)
0x00405440:	leal %esi, -16(%ebp)
0x00405443:	leal %edi, (%ecx,%eax,2)
0x00405446:	movsl %es:(%edi), %ds:(%esi)
0x00405447:	movsw %es:(%edi), %ds:(%esi)
0x00405449:	jmp 0x00405453
0x0040552b:	decl -8(%ebp)
0x0040552e:	subl %eax, $0x4<UINT8>
0x00405531:	subl %ebx, -4(%ebp)
0x00405534:	jmp 0x0040551d
0x00404955:	movl %ecx, 0x8(%ebp)
0x00404958:	pushl $0x1<UINT8>
0x0040495a:	popl %ebx
0x0040495b:	shll %ebx, %cl
0x0040495d:	decl %ebx
0x0040495e:	andl %ebx, %esi
0x00404960:	addl %ebx, 0x2(%edx)
0x00404963:	leal %ecx, (%ebx,%ebx,2)
0x00404966:	leal %edx, (%edx,%ecx,2)
0x00404969:	movzbl %ecx, (%edx)
0x0040496c:	movl 0x8(%ebp), %ecx
0x0040496f:	testl %ecx, %ecx
0x00404971:	movzbl %ecx, 0x1(%edx)
0x00404975:	je 0x00404aa4
0x00404aa4:	shrl %esi, %cl
0x00404aa6:	subl %eax, %ecx
0x00404aa8:	movb %cl, 0x2(%edx)
0x00404aab:	movb (%edi), %cl
0x00404aad:	incl %edi
0x00404aae:	decl -16(%ebp)
0x0040497b:	shrl %esi, %cl
0x0040497d:	subl %eax, %ecx
0x0040497f:	testb 0x8(%ebp), $0x10<UINT8>
0x00404983:	je 0x00404948
0x004049a3:	pushl $0x16<UINT8>
0x004049a5:	popl %ecx
0x004049a6:	subl %ecx, %eax
0x004049a8:	shrl %ecx, $0x3<UINT8>
0x004049ab:	addl -12(%ebp), %ecx
0x004049ae:	movl %ecx, -4(%ebp)
0x004049b1:	movzbl %edx, (%ecx)
0x004049b4:	movl %ecx, %eax
0x004049b6:	addl %eax, $0x8<UINT8>
0x004049b9:	shll %edx, %cl
0x004049bb:	orl %esi, %edx
0x004049bd:	incl -4(%ebp)
0x004049c0:	cmpl %eax, $0xf<UINT8>
0x004049c3:	jb -23
0x00404948:	movl %ebx, -4(%ebp)
0x004022e3:	call 0x00402337
0x00402337:	xorl %eax, %eax
0x00402339:	popl %esi
0x0040233a:	movl %ebx, %fs:(%eax)
0x0040233d:	movl %ebx, (%ebx)
0x0040233f:	leal %esp, -38(%ebx)
0x00402342:	movl %ebp, 0x8(%ebx)
0x00402345:	movl %ebp, -8(%ebp)
0x00402348:	leal %ecx, 0x2da(%esi)
0x0040234e:	movl 0x4(%ebx), %ecx
0x00402351:	movl %fs:0, %ebx
0x00402358:	movl %edi, (%esp)
0x0040235b:	pushl 0x8(%edi)
0x0040235e:	call GlobalFree@kernel32.dll
GlobalFree@kernel32.dll: API Node	
0x00402364:	addl %edi, $0x106<UINT32>
0x0040236a:	pushl $0xe<UINT8>
0x0040236c:	popl %ecx
0x0040236d:	rep movsb %es:(%edi), %ds:(%esi)
0x0040236f:	pushl (%ebx)
0x00402371:	pushl %esi
0x00402372:	pushl %edi
0x00402373:	leal %esi, 0x1530(%edi)
0x00402379:	movl %ecx, %esi
0x0040237b:	subl %ecx, %edi
0x0040237d:	rep stosb %es:(%edi), %al
0x0040237f:	pusha
0x00402380:	jmp %eax
0x004025c2:	xorl %eax, %eax
0x004025c4:	movl %ebx, %fs:(%eax)
0x004025c7:	movl %ebx, (%ebx)
0x004025c9:	leal %esp, -82(%ebx)
0x004025cc:	popa
0x004025cd:	cmpl (%esi), $0x0<UINT8>
0x004025d0:	je 0x00402382
0x004025d6:	movl %edi, 0x8(%esi)
0x004025d9:	addl %edi, %ebp
0x004025db:	movl %ecx, 0xc(%esi)
0x004025de:	sarl %ecx
0x004025e0:	pushl %ecx
0x004025e1:	jb 0x004025f8
0x004025e3:	addl %edi, 0x4(%esi)
0x004025e6:	sarl %ecx, $0x2<UINT8>
0x004025e9:	xorl %eax, %eax
0x004025eb:	rep stosl %es:(%edi), %eax
0x004025ed:	popl %ecx
0x004025ee:	andl %ecx, $0x3<UINT8>
0x004025f1:	rep stosb %es:(%edi), %al
0x004025f3:	addl %esi, $0x14<UINT8>
0x004025f6:	jmp 0x004025cd
0x004025f8:	movl %ebx, 0x4(%esi)
0x004025fb:	subl %ebx, $0x6<UINT8>
0x004025fe:	xorl %edx, %edx
0x00402600:	cmpl %edx, %ebx
0x00402602:	jnl 0x004025e3
0x00402604:	movb %al, (%edx,%edi)
0x00402607:	incl %edx
0x00402608:	cmpb %al, $0xffffffe8<UINT8>
0x0040260a:	je 0x0040261e
0x0040260c:	cmpb %al, $0xffffffe9<UINT8>
0x0040260e:	je 14
0x00402610:	cmpb %al, $0xf<UINT8>
0x00402612:	jne 0x00402600
0x0040261e:	movl %eax, (%edx,%edi)
0x00402621:	cmpb %al, $0x0<UINT8>
0x00402623:	jne -37
0x00402625:	shrw %ax, $0x8<UINT8>
0x00402629:	roll %eax, $0x10<UINT8>
0x0040262c:	xchgb %ah, %al
0x0040262e:	addl %edx, $0x4<UINT8>
0x00402631:	subl %eax, %edx
0x00402633:	movl -4(%edx,%edi), %eax
0x00402637:	jmp 0x00402600
0x00402382:	popl %ebx
0x00402383:	popl %edx
0x00402384:	popl %fs:0
0x0040238b:	popl %eax
0x0040238c:	pushl $0x2<UINT8>
0x0040238e:	pushl %ebx
0x0040238f:	xorl %ebx, %ebx
0x00402391:	pushl $0x34d<UINT32>
0x00402396:	movl %ecx, (%esp)
0x00402399:	btl %ebx, $0x0<UINT8>
0x0040239d:	jb 0x004023b5
0x0040239f:	movl %esi, %fs:0x1c
0x004023a6:	btrl %esi, $0x0<UINT8>
0x004023aa:	addl %esi, %fs:0x22
0x004023b1:	incl %esi
0x004023b2:	xorw %bx, %si
0x004023b5:	xorb %bl, (%ecx,%edx)
0x004023b8:	roll %ebx, $0x1f<UINT8>
0x004023bb:	decl %ecx
0x004023bc:	jnl 0x00402399
0x004023be:	leal %ecx, 0x104(%eax)
0x004023c4:	xorl (%ecx), %ebx
0x004023c6:	xorl 0x4(%ecx), %ebx
0x004023c9:	xorl 0x8(%ecx), %ebx
0x004023cc:	xorl 0xc(%ecx), %ebx
0x004023cf:	popl %ecx
0x004023d0:	xorl 0x1(%ecx,%edx), %ebx
0x004023d4:	xorl %ebx, %ebx
0x004023d6:	movl %esi, %edx
0x004023d8:	cmpl -8726(%edx), $0x410f<UINT32>
0x004023e2:	jne 33
0x004023e4:	subl %esi, $0x223e<UINT32>
0x004023ea:	movzbl %ecx, 0x6(%esi)
0x004023ee:	imull %ecx, %ecx, $0xa<UINT8>
0x004023f1:	addw %cx, $0x3e<UINT16>
0x004023f6:	xorl %ebx, (%esi)
0x004023f8:	roll %ebx, %cl
0x004023fa:	addl %esi, $0x4<UINT8>
0x004023fd:	decl %ecx
0x004023fe:	jne 0x004023f6
0x00402400:	cmpl 0x4(%eax), %ebx
0x00402403:	je 0x0040240d
0x0040240d:	leal %esi, 0x206c(%ebp)
0x00402413:	leal %ecx, 0x800(%ebp)
0x00402419:	movl %ebx, %eax
0x0040241b:	cmpl (%esi), $0x0<UINT8>
0x0040241e:	je 0x00402639
0x00402424:	pushl %ecx
0x00402425:	pushl %ecx
0x00402426:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x0040242c:	testl %eax, %eax
0x0040242e:	jne 0x00402441
0x00402441:	movl %edi, %eax
0x00402443:	addl %eax, 0x3c(%eax)
0x00402446:	movl %eax, 0x78(%eax)
0x00402449:	pushl 0x18(%eax,%edi)
0x0040244d:	movl %ecx, 0x24(%eax,%edi)
0x00402451:	addl %ecx, %edi
0x00402453:	pushl %ecx
0x00402454:	movl %ecx, 0x20(%eax,%edi)
0x00402458:	addl %ecx, %edi
0x0040245a:	pushl %ecx
0x0040245b:	pushl 0x10(%eax,%edi)
0x0040245f:	pushl 0x14(%eax,%edi)
0x00402463:	movl %eax, 0x1c(%eax,%edi)
0x00402467:	addl %eax, %edi
0x00402469:	pushl %eax
0x0040246a:	pushl %esi
0x0040246b:	movl %esi, (%esi)
0x0040246d:	addl %esi, %ebp
0x0040246f:	movl %eax, (%esi)
0x00402471:	testl %eax, %eax
0x00402473:	je 0x004024fe
0x00402479:	jns 0x004024aa
0x004024aa:	addl %eax, %esi
0x004024ac:	pushl %eax
0x004024ad:	pushl %eax
0x004024ae:	pushl %edi
0x004024af:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x004024b5:	testl %eax, %eax
0x004024b7:	je 130
0x004024bd:	decl 0x28(%esp)
0x004024c1:	jnl 0x004024e2
0x004024e2:	movl (%esi), %eax
0x004024e4:	xchgl (%esp), %edi
0x004024e7:	orl %ecx, $0xffffffff<UINT8>
0x004024ea:	xorl %eax, %eax
0x004024ec:	repn scasb %al, %es:(%edi)
0x004024ee:	std
0x004024ef:	notl %ecx
0x004024f1:	decl %edi
0x004024f2:	rep stosb %es:(%edi), %al
0x004024f4:	popl %edi
0x004024f5:	cld
0x004024f6:	addl %esi, $0x4<UINT8>
0x004024f9:	jmp 0x0040246f
0x004024c3:	movl %edx, 0x24(%esp)
0x004024c7:	movb (%edx), $0xffffffe9<UINT8>
0x004024ca:	subl %eax, %edx
0x004024cc:	subl %eax, $0x5<UINT8>
0x004024cf:	movl 0x1(%edx), %eax
0x004024d2:	movl %eax, %edx
0x004024d4:	addl %edx, $0x5<UINT8>
0x004024d7:	movl 0x24(%esp), %edx
0x004024db:	andl %edx, $0x7<UINT8>
0x004024de:	movl 0x28(%esp), %edx
0x004024fe:	popl %esi
0x004024ff:	addl %esp, $0x18<UINT8>
0x00402502:	movl %edx, (%esi)
0x00402504:	addl %edx, %ebp
0x00402506:	leal %eax, 0x110(%ebx)
0x0040250c:	movl %ecx, 0x4(%esp)
0x00402510:	cmpl (%edx), $0x0<UINT8>
0x00402513:	je 0x00402527
0x00402515:	cmpl %ebx, (%edx)
0x00402517:	sbbl (%eax), $0x0<UINT8>
0x0040251a:	cmpl (%edx), %ecx
0x0040251c:	sbbl (%eax), $0x0<UINT8>
0x0040251f:	addl %edx, $0x4<UINT8>
0x00402522:	rorl (%eax), $0x3<UINT8>
0x00402525:	jmp 0x00402510
0x00402527:	movl (%esi), $0x0<UINT32>
0x0040252d:	popl %edi
0x0040252e:	orl %ecx, $0xffffffff<UINT8>
0x00402531:	xorl %eax, %eax
0x00402533:	repn scasb %al, %es:(%edi)
0x00402535:	movl %ecx, %edi
0x00402537:	addl %esi, $0x4<UINT8>
0x0040253a:	jmp 0x0040241b
0x00402639:	popl %ecx
0x0040263a:	popl %esi
0x0040263b:	std
0x0040263c:	xorl %eax, %eax
0x0040263e:	movl %ecx, $0x366<UINT32>
0x00402643:	call 0x00404106
0x00404106:	popl %edi
0x00404107:	rep stosb %es:(%edi), %al
0x00404109:	popa
0x0040410a:	popfw
0x0040410c:	addl %esp, $0xc<UINT8>
0x0040410f:	jmp 0x00401000
0x00401000:	pushl %eax
0x00401001:	pushl %ebx
0x00401002:	leal %eax, 0x403098
0x00401008:	pushl %eax
0x00401009:	call 0x004011f2
0x004011f2:	jmp SetCurrentDirectoryA@kernel32.dll
SetCurrentDirectoryA@kernel32.dll: API Node	
0x0040100e:	leal %eax, 0x40306d
0x00401014:	pushl $0x0<UINT8>
0x00401016:	pushl $0x80<UINT32>
0x0040101b:	pushl $0x2<UINT8>
0x0040101d:	pushl $0x0<UINT8>
0x0040101f:	pushl $0x1<UINT8>
0x00401021:	pushl $0x40000000<UINT32>
0x00401026:	pushl %eax
0x00401027:	call 0x00401180
0x00401180:	jmp 0x00404114
0x00404114:	jmp CreateFileA@kernel32.dll
CreateFileA@kernel32.dll: API Node	
0x0040102c:	movl %ebx, %eax
0x0040102e:	pushl $0x0<UINT8>
0x00401030:	pushl $0x0<UINT8>
0x00401032:	pushl $0xa<UINT8>
0x00401034:	leal %eax, 0x403008
0x0040103a:	pushl %eax
0x0040103b:	pushl %ebx
0x0040103c:	call 0x004011fe
0x004011fe:	jmp WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x00401041:	pushl %ebx
0x00401042:	call 0x00401174
0x00401174:	jmp CloseHandle@kernel32.dll
CloseHandle@kernel32.dll: API Node	
0x00401047:	pushl $0x0<UINT8>
0x00401049:	leal %eax, 0x403080
0x0040104f:	pushl %eax
0x00401050:	leal %eax, 0x403076
0x00401056:	pushl %eax
0x00401057:	call 0x0040117a
0x0040117a:	jmp CopyFileA@kernel32.dll
CopyFileA@kernel32.dll: API Node	
0x0040105c:	leal %eax, 0x403090
0x00401062:	pushl %eax
0x00401063:	leal %eax, 0x40308a
0x00401069:	pushl %eax
0x0040106a:	call 0x00401198
0x00401198:	jmp FindFirstFileA@kernel32.dll
FindFirstFileA@kernel32.dll: API Node	
0x0040106f:	leal %ebx, 0x403090
0x00401075:	pushl %ebx
0x00401076:	pushl %eax
0x00401077:	call 0x0040119e
0x0040119e:	jmp FindNextFileA@kernel32.dll
FindNextFileA@kernel32.dll: API Node	
0x0040107c:	pushl %eax
0x0040107d:	call 0x00401192
0x00401192:	jmp FindClose@kernel32.dll
FindClose@kernel32.dll: API Node	
0x00401082:	leal %eax, 0x40306d
0x00401088:	pushl %eax
0x00401089:	call 0x004011b0
0x004011b0:	jmp GetFileAttributesA@kernel32.dll
GetFileAttributesA@kernel32.dll: API Node	
0x0040108e:	pushl $0x80<UINT32>
0x00401093:	leal %eax, 0x40306d
0x00401099:	pushl %eax
0x0040109a:	call 0x004011f8
0x004011f8:	jmp 0x0040412d
0x0040412d:	jmp SetFileAttributesA@kernel32.dll
SetFileAttributesA@kernel32.dll: API Node	
0x0040109f:	leal %eax, 0x40306d
0x004010a5:	pushl %eax
0x004010a6:	call 0x004011b0
0x004010ab:	leal %eax, 0x40306d
0x004010b1:	pushl %eax
0x004010b2:	call 0x00401186
0x00401186:	jmp DeleteFileA@kernel32.dll
DeleteFileA@kernel32.dll: API Node	
0x004010b7:	leal %eax, 0x403060
0x004010bd:	pushl %eax
0x004010be:	call 0x004011ce
0x004011ce:	jmp 0x00404123
0x00404123:	jmp GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x004010c3:	call 0x004011b6
0x004011b6:	jmp 0x0040411e
0x0040411e:	jmp GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x004010c8:	call 0x004011e0
0x004011e0:	jmp GetVersion@kernel32.dll
GetVersion@kernel32.dll: API Node	
0x004010cd:	leal %ebx, 0x403042
0x004010d3:	pushl $0x64<UINT8>
0x004010d5:	pushl %ebx
0x004010d6:	call 0x004011d4
0x004011d4:	jmp 0x00404128
0x00404128:	jmp GetSystemDirectoryA@kernel32.dll
GetSystemDirectoryA@kernel32.dll: API Node	
0x004010db:	leal %ebx, 0x403042
0x004010e1:	pushl %ebx
0x004010e2:	pushl $0x64<UINT8>
0x004010e4:	call 0x004011aa
0x004011aa:	jmp GetCurrentDirectoryA@kernel32.dll
GetCurrentDirectoryA@kernel32.dll: API Node	
0x004010e9:	leal %ebx, 0x403042
0x004010ef:	pushl $0x64<UINT8>
0x004010f1:	pushl %ebx
0x004010f2:	call 0x004011e6
0x004011e6:	jmp GetWindowsDirectoryA@kernel32.dll
GetWindowsDirectoryA@kernel32.dll: API Node	
0x004010f7:	call 0x004011a4
0x004011a4:	jmp GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004010fc:	leal %eax, 0x403054
0x00401102:	pushl %eax
0x00401103:	call 0x004011da
0x004011da:	jmp GetSystemTime@kernel32.dll
GetSystemTime@kernel32.dll: API Node	
0x00401108:	leal %eax, 0x403022
0x0040110e:	pushl %eax
0x0040110f:	call 0x004011ec
0x004011ec:	jmp LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00401114:	leal %eax, 0x403015
0x0040111a:	pushl %eax
0x0040111b:	call 0x004011ec
0x00401120:	leal %ebx, 0x40302d
0x00401126:	pushl %ebx
0x00401127:	pushl %eax
0x00401128:	call 0x004011c8
0x004011c8:	jmp GetProcAddress@kernel32.dll
0x0040112d:	leal %ebx, 0x403042
0x00401133:	pushl %ebx
0x00401134:	pushl $0x64<UINT8>
0x00401136:	call 0x004011aa
0x0040113b:	pushl $0x64<UINT8>
0x0040113d:	pushl $0x403000<UINT32>
0x00401142:	pushl $0x0<UINT8>
0x00401144:	call 0x004011bc
0x004011bc:	jmp GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00401149:	leal %eax, 0x403008
0x0040114f:	movl %ebx, $0x0<UINT32>
0x00401154:	pushl %ebx
0x00401155:	pushl %eax
0x00401156:	pushl %eax
0x00401157:	pushl %ebx
0x00401158:	call 0x00401204
0x00401204:	jmp MessageBoxA@user32.dll
MessageBoxA@user32.dll: API Node	
0x0040115d:	pushl $0x0<UINT8>
0x0040115f:	call 0x004011c2
0x004011c2:	jmp GetModuleHandleA@kernel32.dll
0x00401164:	addl %eax, $0x116b<UINT32>
0x00401169:	pushl %eax
0x0040116a:	ret

0x0040116b:	addl %eax, %ebx
0x0040116d:	popl %eax
0x0040116e:	call 0x0040118c
0x0040118c:	jmp 0x00404119
0x00404119:	jmp ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
