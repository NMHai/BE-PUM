0x00402436:	pushl $0x18<UINT8>
0x00402438:	pushl $0x40a2f0<UINT32>
0x0040243d:	call 0x004037f8
0x004037f8:	pushl $0x40384c<UINT32>
0x004037fd:	movl %eax, %fs:0
0x00403803:	pushl %eax
0x00403804:	movl %eax, 0x10(%esp)
0x00403808:	movl 0x10(%esp), %ebp
0x0040380c:	leal %ebp, 0x10(%esp)
0x00403810:	subl %esp, %eax
0x00403812:	pushl %ebx
0x00403813:	pushl %esi
0x00403814:	pushl %edi
0x00403815:	movl %eax, -8(%ebp)
0x00403818:	movl -24(%ebp), %esp
0x0040381b:	pushl %eax
0x0040381c:	movl %eax, -4(%ebp)
0x0040381f:	movl -4(%ebp), $0xffffffff<UINT32>
0x00403826:	movl -8(%ebp), %eax
0x00403829:	leal %eax, -16(%ebp)
0x0040382c:	movl %fs:0, %eax
0x00403832:	ret

0x00402442:	movl %edi, $0x94<UINT32>
0x00402447:	movl %eax, %edi
0x00402449:	call 0x00405330
0x00405330:	cmpl %eax, $0x1000<UINT32>
0x00405335:	jae 14
0x00405337:	negl %eax
0x00405339:	addl %eax, %esp
0x0040533b:	addl %eax, $0x4<UINT8>
0x0040533e:	testl (%eax), %eax
0x00405340:	xchgl %esp, %eax
0x00405341:	movl %eax, (%eax)
0x00405343:	pushl %eax
0x00405344:	ret

0x0040244e:	movl -24(%ebp), %esp
0x00402451:	movl %esi, %esp
0x00402453:	movl (%esi), %edi
0x00402455:	pushl %esi
0x00402456:	call GetVersionExA@kernel32.dll
GetVersionExA@kernel32.dll: API Node	
0x0040245c:	movl %ecx, 0x10(%esi)
0x0040245f:	movl 0x40ccc4, %ecx
0x00402465:	movl %eax, 0x4(%esi)
0x00402468:	movl 0x40ccd0, %eax
0x0040246d:	movl %edx, 0x8(%esi)
0x00402470:	movl 0x40ccd4, %edx
0x00402476:	movl %esi, 0xc(%esi)
0x00402479:	andl %esi, $0x7fff<UINT32>
0x0040247f:	movl 0x40ccc8, %esi
0x00402485:	cmpl %ecx, $0x2<UINT8>
0x00402488:	je 0x00402496
0x00402496:	shll %eax, $0x8<UINT8>
0x00402499:	addl %eax, %edx
0x0040249b:	movl 0x40cccc, %eax
0x004024a0:	xorl %edi, %edi
0x004024a2:	pushl %edi
0x004024a3:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x004024a9:	cmpw (%eax), $0x5a4d<UINT16>
0x004024ae:	jne 31
0x004024b0:	movl %ecx, 0x3c(%eax)
0x004024b3:	addl %ecx, %eax
0x004024b5:	cmpl (%ecx), $0x4550<UINT32>
0x004024bb:	jne 18
0x004024bd:	movzwl %eax, 0x18(%ecx)
0x004024c1:	cmpl %eax, $0x10b<UINT32>
0x004024c6:	je 0x004024e7
0x004024e7:	cmpl 0x74(%ecx), $0xe<UINT8>
0x004024eb:	jbe -30
0x004024ed:	xorl %eax, %eax
0x004024ef:	cmpl 0xe8(%ecx), %edi
0x004024f5:	setne %al
0x004024f8:	movl -28(%ebp), %eax
0x004024fb:	pushl $0x1<UINT8>
0x004024fd:	call 0x004052dc
0x004052dc:	xorl %eax, %eax
0x004052de:	cmpl 0x4(%esp), %eax
0x004052e2:	pushl $0x0<UINT8>
0x004052e4:	sete %al
0x004052e7:	pushl $0x1000<UINT32>
0x004052ec:	pushl %eax
0x004052ed:	call HeapCreate@kernel32.dll
HeapCreate@kernel32.dll: API Node	
0x004052f3:	testl %eax, %eax
0x004052f5:	movl 0x40d3bc, %eax
0x004052fa:	je 42
0x004052fc:	call 0x004052c2
0x004052c2:	cmpl 0x40ccc4, $0x2<UINT8>
0x004052c9:	jne 13
0x004052cb:	cmpl 0x40ccd0, $0x5<UINT8>
0x004052d2:	jb 4
0x004052d4:	xorl %eax, %eax
0x004052d6:	incl %eax
0x004052d7:	ret

0x00405301:	cmpl %eax, $0x3<UINT8>
0x00405304:	movl 0x40d3c0, %eax
0x00405309:	jne 0x00405329
0x00405329:	xorl %eax, %eax
0x0040532b:	incl %eax
0x0040532c:	ret

0x00402502:	popl %ecx
0x00402503:	testl %eax, %eax
0x00402505:	jne 0x0040250f
0x0040250f:	call 0x00404417
0x00404417:	call 0x0040536d
0x0040536d:	pushl %esi
0x0040536e:	pushl %edi
0x0040536f:	xorl %esi, %esi
0x00405371:	movl %edi, $0x40ce20<UINT32>
0x00405376:	cmpl 0x40c664(,%esi,8), $0x1<UINT8>
0x0040537e:	jne 30
0x00405380:	leal %eax, 0x40c660(,%esi,8)
0x00405387:	movl (%eax), %edi
0x00405389:	pushl $0xfa0<UINT32>
0x0040538e:	pushl (%eax)
0x00405390:	addl %edi, $0x18<UINT8>
0x00405393:	call 0x00407454
0x00407454:	pushl $0x10<UINT8>
0x00407456:	pushl $0x40b0b0<UINT32>
0x0040745b:	call 0x004037f8
0x00407460:	movl %eax, 0x40cfe8
0x00407465:	testl %eax, %eax
0x00407467:	jne 55
0x00407469:	cmpl 0x40ccc4, $0x1<UINT8>
0x00407470:	je 36
0x00407472:	pushl $0x40a474<UINT32>
0x00407477:	call GetModuleHandleA@kernel32.dll
0x0040747d:	testl %eax, %eax
0x0040747f:	je 21
0x00407481:	pushl $0x40b084<UINT32>
0x00407486:	pushl %eax
0x00407487:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x0040748d:	movl 0x40cfe8, %eax
0x00407492:	testl %eax, %eax
0x00407494:	jne 0x004074a0
0x004074a0:	andl -4(%ebp), $0x0<UINT8>
0x004074a4:	pushl 0xc(%ebp)
0x004074a7:	pushl 0x8(%ebp)
0x004074aa:	call InitializeCriticalSectionAndSpinCount@kernel32.dll
InitializeCriticalSectionAndSpinCount@kernel32.dll: API Node	
0x004074ac:	movl -32(%ebp), %eax
0x004074af:	jmp 0x004074d5
0x004074d5:	orl -4(%ebp), $0xffffffff<UINT8>
0x004074d9:	call 0x00403833
0x00403833:	movl %ecx, -16(%ebp)
0x00403836:	movl %fs:0, %ecx
0x0040383d:	popl %ecx
0x0040383e:	popl %edi
0x0040383f:	popl %esi
0x00403840:	popl %ebx
0x00403841:	leave
0x00403842:	pushl %ecx
0x00403843:	ret

0x004074de:	ret

0x00405398:	testl %eax, %eax
0x0040539a:	popl %ecx
0x0040539b:	popl %ecx
0x0040539c:	je 0x004053aa
0x0040539e:	incl %esi
0x004053aa:	andl 0x40c660(,%esi,8), $0x0<UINT8>
0x0040384c:	pushl %ebp
0x0040384d:	movl %ebp, %esp
0x0040384f:	subl %esp, $0x8<UINT8>
0x00403852:	pushl %ebx
0x00403853:	pushl %esi
0x00403854:	pushl %edi
0x00403855:	pushl %ebp
0x00403856:	cld
0x00403857:	movl %ebx, 0xc(%ebp)
0x0040385a:	movl %eax, 0x8(%ebp)
0x0040385d:	testl 0x4(%eax), $0x6<UINT32>
0x00403864:	jne 171
0x0040386a:	movl -8(%ebp), %eax
0x0040386d:	movl %eax, 0x10(%ebp)
0x00403870:	movl -4(%ebp), %eax
0x00403873:	leal %eax, -8(%ebp)
0x00403876:	movl -4(%ebx), %eax
0x00403879:	movl %esi, 0xc(%ebx)
0x0040387c:	movl %edi, 0x8(%ebx)
0x0040387f:	pushl %ebx
0x00403880:	call 0x00406875
0x00406875:	pushl %ebp
0x00406876:	movl %ebp, %esp
0x00406878:	subl %esp, $0x20<UINT8>
0x0040687b:	pushl %ebx
0x0040687c:	pushl %esi
0x0040687d:	movl %esi, 0x8(%ebp)
0x00406880:	movl %ebx, 0x8(%esi)
0x00406883:	testb %bl, $0x3<UINT8>
0x00406886:	jne 27
0x00406888:	movl %eax, %fs:0x18
0x0040688e:	movl 0x8(%ebp), %eax
0x00406891:	movl %eax, 0x8(%ebp)
0x00406894:	movl %ecx, 0x8(%eax)
0x00406897:	cmpl %ebx, %ecx
0x00406899:	movl -4(%ebp), %ecx
0x0040689c:	jb 12
0x0040689e:	cmpl %ebx, 0x4(%eax)
0x004068a1:	jae 0x004068aa
0x004068aa:	pushl %edi
0x004068ab:	movl %edi, 0xc(%esi)
0x004068ae:	cmpl %edi, $0xffffffff<UINT8>
0x004068b1:	jne 8
0x004068b3:	xorl %eax, %eax
0x004068b5:	incl %eax
0x004068b6:	jmp 0x00406a99
0x00406a99:	popl %edi
0x00406a9a:	popl %esi
0x00406a9b:	popl %ebx
0x00406a9c:	leave
0x00406a9d:	ret

0x00403885:	addl %esp, $0x4<UINT8>
0x00403888:	orl %eax, %eax
0x0040388a:	je 123
0x0040388c:	cmpl %esi, $0xffffffff<UINT8>
0x0040388f:	je 0x0040390e
0x0040390e:	movl %eax, $0x1<UINT32>
0x00403913:	jmp 0x0040392a
0x0040392a:	popl %ebp
0x0040392b:	popl %edi
0x0040392c:	popl %esi
0x0040392d:	popl %ebx
0x0040392e:	movl %esp, %ebp
0x00403930:	popl %ebp
0x00403931:	ret

0x004053b2:	xorl %eax, %eax
0x004053b4:	jmp 0x004053a7
0x004053a7:	popl %edi
0x004053a8:	popl %esi
0x004053a9:	ret

0x0040441c:	testl %eax, %eax
0x0040441e:	jne 0x00404428
0x00404420:	call 0x00404200
0x00404200:	movl %eax, 0x40c3a8
0x00404205:	cmpl %eax, $0xffffffff<UINT8>
0x00404208:	je 0x00404218
0x00404218:	jmp 0x004053b6
0x004053b6:	pushl %ebx
0x004053b7:	movl %ebx, 0x40a09c
0x004053bd:	pushl %esi
0x004053be:	movl %esi, $0x40c660<UINT32>
0x004053c3:	pushl %edi
0x004053c4:	movl %edi, (%esi)
0x004053c6:	testl %edi, %edi
0x004053c8:	je 0x004053dd
0x004053ca:	cmpl 0x4(%esi), $0x1<UINT8>
0x004053ce:	je 0x004053dd
0x004053dd:	addl %esi, $0x8<UINT8>
0x004053e0:	cmpl %esi, $0x40c780<UINT32>
0x004053e6:	jl 0x004053c4
0x004053e8:	movl %esi, $0x40c660<UINT32>
0x004053ed:	popl %edi
0x004053ee:	movl %eax, (%esi)
0x004053f0:	testl %eax, %eax
0x004053f2:	je 0x004053fd
0x004053f4:	cmpl 0x4(%esi), $0x1<UINT8>
0x004053f8:	jne 3
0x004053fa:	pushl %eax
0x004053fb:	call DeleteCriticalSection@kernel32.dll
DeleteCriticalSection@kernel32.dll: API Node	
0x004053fd:	addl %esi, $0x8<UINT8>
0x00405400:	cmpl %esi, $0x40c780<UINT32>
0x00405406:	jl 0x004053ee
0x00405408:	popl %esi
0x00405409:	popl %ebx
0x0040540a:	ret

0x00404425:	xorl %eax, %eax
0x00404427:	ret

0x00402514:	testl %eax, %eax
0x00402516:	jne 8
0x00402518:	pushl $0x10<UINT8>
0x0040251a:	call 0x00402412
0x00402412:	cmpl 0x40cc98, $0x2<UINT8>
0x00402419:	je 5
0x0040241b:	call 0x00404c0c
0x00404c0c:	movl %eax, 0x40cc98
0x00404c11:	cmpl %eax, $0x1<UINT8>
0x00404c14:	je 13
0x00404c16:	testl %eax, %eax
0x00404c18:	jne 42
0x00404c1a:	cmpl 0x40c0f8, $0x1<UINT8>
0x00404c21:	jne 33
0x00404c23:	pushl $0xfc<UINT32>
0x00404c28:	call 0x00404a95
0x00404a95:	pushl %ebp
0x00404a96:	leal %ebp, -140(%esp)
0x00404a9d:	subl %esp, $0x10c<UINT32>
0x00404aa3:	movl %eax, 0x40c3a4
0x00404aa8:	movl %ecx, 0x94(%ebp)
0x00404aae:	pushl %ebx
0x00404aaf:	pushl %esi
0x00404ab0:	movl 0x88(%ebp), %eax
0x00404ab6:	xorl %edx, %edx
0x00404ab8:	pushl %edi
0x00404ab9:	xorl %eax, %eax
0x00404abb:	cmpl %ecx, 0x40c5a0(,%eax,8)
0x00404ac2:	je 0x00404aca
0x00404ac4:	incl %eax
0x00404ac5:	cmpl %eax, $0x13<UINT8>
0x00404ac8:	jb 0x00404abb
0x00404aca:	movl %esi, %eax
0x00404acc:	shll %esi, $0x3<UINT8>
0x00404acf:	cmpl %ecx, 0x40c5a0(%esi)
0x00404ad5:	jne 277
0x00404adb:	movl %eax, 0x40cc98
0x00404ae0:	cmpl %eax, $0x1<UINT8>
0x00404ae3:	je 223
0x00404ae9:	cmpl %eax, %edx
0x00404aeb:	jne 13
0x00404aed:	cmpl 0x40c0f8, $0x1<UINT8>
0x00404af4:	je 0x00404bc8
0x00404bc8:	pushl %edx
0x00404bc9:	leal %eax, 0x94(%ebp)
0x00404bcf:	pushl %eax
0x00404bd0:	leal %esi, 0x40c5a4(%esi)
0x00404bd6:	pushl (%esi)
0x00404bd8:	call 0x004026d0
0x004026d0:	movl %ecx, 0x4(%esp)
0x004026d4:	testl %ecx, $0x3<UINT32>
0x004026da:	je 0x00402700
0x00402700:	movl %eax, (%ecx)
0x00402702:	movl %edx, $0x7efefeff<UINT32>
0x00402707:	addl %edx, %eax
0x00402709:	xorl %eax, $0xffffffff<UINT8>
0x0040270c:	xorl %eax, %edx
0x0040270e:	addl %ecx, $0x4<UINT8>
0x00402711:	testl %eax, $0x81010100<UINT32>
0x00402716:	je 0x00402700
0x00402718:	movl %eax, -4(%ecx)
0x0040271b:	testb %al, %al
0x0040271d:	je 50
0x0040271f:	testb %ah, %ah
0x00402721:	je 36
0x00402723:	testl %eax, $0xff0000<UINT32>
0x00402728:	je 0x0040273d
0x0040273d:	leal %eax, -2(%ecx)
0x00402740:	movl %ecx, 0x4(%esp)
0x00402744:	subl %eax, %ecx
0x00402746:	ret

0x00404bdd:	popl %ecx
0x00404bde:	pushl %eax
0x00404bdf:	pushl (%esi)
0x00404be1:	pushl $0xfffffff4<UINT8>
0x00404be3:	call GetStdHandle@kernel32.dll
GetStdHandle@kernel32.dll: API Node	
0x00404be9:	pushl %eax
0x00404bea:	call WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x00404bf0:	leal %esp, -140(%ebp)
0x00404bf6:	movl %ecx, 0x88(%ebp)
0x00404bfc:	call 0x004067ca
0x004067ca:	cmpl %ecx, 0x40c3a4
0x004067d0:	jne 1
0x004067d2:	ret

0x00404c01:	popl %edi
0x00404c02:	popl %esi
0x00404c03:	popl %ebx
0x00404c04:	addl %ebp, $0x8c<UINT32>
0x00404c0a:	leave
0x00404c0b:	ret

0x00404c2d:	movl %eax, 0x40cd08
0x00404c32:	testl %eax, %eax
0x00404c34:	popl %ecx
0x00404c35:	je 0x00404c39
0x00404c39:	pushl $0xff<UINT32>
0x00404c3e:	call 0x00404a95
0x00404c43:	popl %ecx
0x00404c44:	ret

0x00402420:	pushl 0x4(%esp)
0x00402424:	call 0x00404a95
0x0040272a:	testl %eax, $0xff000000<UINT32>
0x0040272f:	je 0x00402733
0x00402733:	leal %eax, -1(%ecx)
0x00402736:	movl %ecx, 0x4(%esp)
0x0040273a:	subl %eax, %ecx
0x0040273c:	ret

0x00402429:	pushl $0xff<UINT32>
0x0040242e:	call 0x0040462a
0x0040462a:	pushl $0x40a4b4<UINT32>
0x0040462f:	call GetModuleHandleA@kernel32.dll
0x00404635:	testl %eax, %eax
0x00404637:	je 0x0040464f
0x0040464f:	pushl 0x4(%esp)
0x00404653:	call ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
0x0040539f:	cmpl %esi, $0x24<UINT8>
0x004053a2:	jl -46
0x004053a4:	xorl %eax, %eax
0x004053a6:	incl %eax
0x00404428:	pushl %esi
0x00404429:	pushl %edi
0x0040442a:	pushl $0x40a474<UINT32>
0x0040442f:	call GetModuleHandleA@kernel32.dll
0x00404435:	movl %edi, %eax
0x00404437:	testl %edi, %edi
0x00404439:	je 107
0x0040443b:	movl %esi, 0x40a058
0x00404441:	pushl $0x40a468<UINT32>
0x00404446:	pushl %edi
0x00404447:	call GetProcAddress@kernel32.dll
0x00404449:	pushl $0x40a45c<UINT32>
0x0040444e:	pushl %edi
0x0040444f:	movl 0x40ccac, %eax
0x00404454:	call GetProcAddress@kernel32.dll
0x00404456:	pushl $0x40a450<UINT32>
0x0040445b:	pushl %edi
0x0040445c:	movl 0x40ccb0, %eax
0x00404461:	call GetProcAddress@kernel32.dll
0x00404463:	pushl $0x40a448<UINT32>
0x00404468:	pushl %edi
0x00404469:	movl 0x40ccb4, %eax
0x0040446e:	call GetProcAddress@kernel32.dll
0x00404470:	cmpl 0x40ccb0, $0x0<UINT8>
0x00404477:	movl 0x40ccb8, %eax
0x0040447c:	jne 0x004044a6
0x004044a6:	pushl $0x4042a1<UINT32>
0x004044ab:	call FlsAlloc@kernel32.dll
FlsAlloc@kernel32.dll: API Node	
0x004044b1:	cmpl %eax, $0xffffffff<UINT8>
0x004044b4:	movl 0x40c3a8, %eax
0x004044b9:	je 0x004044fc
0x004044bb:	xorl %edi, %edi
0x004044fc:	call 0x00404200
0x0040420a:	pushl %eax
0x00404501:	xorl %eax, %eax
0x00404503:	popl %edi
0x00404504:	popl %esi
0x00404505:	ret

0x00001db1: Unknown Node	
0x0040420b:	call FlsFree@kernel32.dll
FlsFree@kernel32.dll: API Node	
0x00404211:	orl 0x40c3a8, $0xffffffff<UINT8>
0x004044bd:	pushl $0x8c<UINT32>
0x004044c2:	incl %edi
0x004044c3:	pushl %edi
0x004044c4:	call 0x004049da
0x004049da:	pushl $0x10<UINT8>
0x004049dc:	pushl $0x40a4d0<UINT32>
0x004049e1:	call 0x004037f8
0x004049e6:	movl %esi, 0x8(%ebp)
0x004049e9:	imull %esi, 0xc(%ebp)
0x004049ed:	movl -32(%ebp), %esi
0x004049f0:	testl %esi, %esi
0x004049f2:	jne 0x004049f5
0x004049f5:	xorl %edi, %edi
0x004049f7:	movl -28(%ebp), %edi
0x004049fa:	cmpl %esi, $0xffffffe0<UINT8>
0x004049fd:	ja 101
0x004049ff:	cmpl 0x40d3c0, $0x3<UINT8>
0x00404a06:	jne 0x00404a4f
0x00404a4f:	testl %edi, %edi
0x00404a51:	jne 58
0x00404a53:	pushl %esi
0x00404a54:	pushl $0x8<UINT8>
0x00404a56:	pushl 0x40d3bc
0x00404a5c:	call HeapAlloc@kernel32.dll
HeapAlloc@kernel32.dll: API Node	
0x00404a62:	movl %edi, %eax
0x00404a64:	testl %edi, %edi
0x00404a66:	jne 0x00404a8d
0x00404a8d:	movl %eax, %edi
0x00404a8f:	call 0x00403833
0x00404a94:	ret

0x004044c9:	movl %esi, %eax
0x004044cb:	testl %esi, %esi
0x004044cd:	popl %ecx
0x004044ce:	popl %ecx
0x004044cf:	je 43
0x004044d1:	pushl %esi
0x004044d2:	pushl 0x40c3a8
0x004044d8:	call FlsSetValue@kernel32.dll
FlsSetValue@kernel32.dll: API Node	
0x004044de:	testl %eax, %eax
0x004044e0:	je 26
0x004044e2:	movl 0x54(%esi), $0x40c3b0<UINT32>
0x00000000:	null
